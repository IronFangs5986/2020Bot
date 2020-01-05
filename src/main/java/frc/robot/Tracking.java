package frc.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * This code was largely based originally on Asid61's code and his instructions (https://github.com/asid61/JeVois-For-FRC).
 * This code was also largely modified and simplified by FRC team 3847, Spectrum.
 * Special thanks to Bill Kendall from FRC team 2073 and DJ for their great and amazing help to the team.
 */

public class Tracking {

    /* JeVois Camera Number */
    static final int JEVOIS_CAM_NUMBER = 0;

    /* Serial Port Constants */
    static final int BAUD_RATE = 115200;

    /* MJPG Streaming Constants */
    static final int MJPG_STREAM_PORT = 1180;

    /* JeVois Program Selection Constants - must align with JeVois .cfg files */
    static final int MAPPING_WIDTH_PXL_1 = 320;
    static final int MAPPING_HEIGHT_PXL_1 = 240;
    static final int MAPPING_FRMRT_FPS_1 = 30;

    /* JeVois Program Selection Constants - must align with JeVois .cfg files */
    static final int MAPPING_WIDTH_PXL_2 = 320;
    static final int MAPPING_HEIGHT_PXL_2 = 240;
    static final int MAPPING_FRMRT_FPS_2 = 15;

    /* Packet format constants */
    static final String PACKET_START_CHAR = "{";
    static final String PACKET_END_CHAR = "}";
    static final String PACKET_DILEM_CHAR = ",";

    /* Serial port used for getting target data from JeVois */
    SerialPort visionPort = null;

    /* USBCam and server used for broadcasting a webstream of what is seen */
    UsbCamera visionCam = null;
    MjpegServer camServer = null;

    /* Status variables */
    boolean camStreamRunning = false;
    boolean trackingOnline = false;
    boolean trackingEnable = true;
    boolean debugOutEnable = false;

    /* Most recently seen target */
    private double tx; // x coordinate of target center
    private double ty; // y coordinate of target center
    private double ta; // area of target
    private boolean tv; // valid target
    private boolean conn; // Has received a valid packet. Has a valid connection
    private double camMode; // Camera mode either vision processing, driver mode, or another vision

    /*
     * Processing mode
     */
    public Tracking() {
        /* ConnectJeVois and Check if we are connected */
        connectJeVois();
        checkJeVoisConnection();

        /* Start listening for packets */
        System.out.println("Starting JeVois Listener Threads");
        jevoisListenerThread.start();
    }

    /*
     * Connect to the JeVois Camera
     */
    public void connectJeVois() {

        int retry_counter = 0;

        /*
         * Retry strategy to get this serial port open or switch to the other usb ports
         */
        while (visionPort == null && retry_counter++ < 10) {
            try {
                System.out.print("Creating JeVois SerialPort...");
                visionPort = new SerialPort(BAUD_RATE, SerialPort.Port.kUSB);
                System.out.println("SUCCESS USB 0");
            } catch (Exception e) {
                try {
                    System.out.print("Creating JeVois SerialPort...");
                    visionPort = new SerialPort(BAUD_RATE, SerialPort.Port.kUSB1);
                    System.out.println("SUCCESS USB 1");
                } catch (Exception f) {
                    try {
                        System.out.print("Creating JeVois SerialPort...");
                        visionPort = new SerialPort(BAUD_RATE, SerialPort.Port.kUSB2);
                        System.out.println("SUCCESS USB 2");
                    } catch (Exception g) {
                        System.out.println("FAILED!!");
                        System.out.println("Failed to connect to JeVois on any port!");
                        sleep(500);
                        System.out.println("Retry " + Integer.toString(retry_counter));
                    }
                }
            }
        }
    }

    /*
     * Check if we are able to communicate with the JeVois Camera
     */
    public boolean checkJeVoisConnection() {

        /* Test to make sure we are actually talking to the JeVois */
        if (sendPing() != 0) {
            DriverStation.reportError("JeVois ping test failed. Not starting vision system.", false);
            return false;
        }

        System.out.println("JeVois Connection Good!");
        return true;
    }

    /*
     * Open Mjpeg streamer 1 from the JeVois camera
     */
    public void startCameraStream1() {

        /*
         * Disables the full serOut so that we can parse the packets and use the
         * tracking documents
         */
        stopCameraStream();
        debugOutEnable = false;

        try {
            System.out.print("Starting JeVois Cam Stream 1...");
            visionCam = new UsbCamera("VisionProcCam", JEVOIS_CAM_NUMBER);
            visionCam.setVideoMode(PixelFormat.kMJPEG, MAPPING_WIDTH_PXL_1, MAPPING_HEIGHT_PXL_1, MAPPING_FRMRT_FPS_1);
            camServer = new MjpegServer("VisionCamServer", MJPG_STREAM_PORT);
            camServer.setSource(visionCam);
            camStreamRunning = true;
            trackingEnable = true;
            System.out.println("Vision Cam Stream 1 Opened!!");
        } catch (Exception e) {
            DriverStation.reportError("Cannot start camera stream 1 from JeVois", false);
            e.printStackTrace();
        }
    }

    /*
     * Open Mjpeg streamer 2 from the JeVois camera
     */
    public void startCameraStream2() {

        /*
         * Turns on the serout so that we can see the full serial output of the stream
         */
        stopCameraStream();
        debugOutEnable = true;

        try {
            System.out.print("Starting JeVois Cam Stream 2...");
            visionCam = new UsbCamera("VisionProcCam", JEVOIS_CAM_NUMBER);
            visionCam.setVideoMode(PixelFormat.kMJPEG, MAPPING_WIDTH_PXL_2, MAPPING_HEIGHT_PXL_2, MAPPING_FRMRT_FPS_2);
            camServer = new MjpegServer("VisionCamServer", MJPG_STREAM_PORT);
            camServer.setSource(visionCam);
            camStreamRunning = true;
            trackingEnable = false;
            System.out.println("Vision Cam Stream 2 Opened!!");
        } catch (Exception e) {
            DriverStation.reportError("Cannot start camera stream 2 from JeVois", false);
            e.printStackTrace();
        }
    }

    /*
     * Cease the operation of the camera stream. Unknown if needed.
     */
    public void stopCameraStream() {
        if (camStreamRunning) {
            camServer.close();
            visionCam.close();
            camStreamRunning = false;
        }
    }

    /*
     * Sets wether to print the serial output in the console
     */
    public void setSerOutEnable(boolean enable) {

        debugOutEnable = enable;

    }

    /*
     * This is the main perodic update function for the Listener. It is intended to
     * be run in a background task, as it will block until it gets packets.
     */
    private void backgroundUpdate() {
        /* Debug - just print whatever we get on the serial port */
        if (debugOutEnable) {
            System.out.println("Debugging:");
            blockAndPrintAllSerial();
        } else {

            /* Real code - Grab packets and parse them. */
            String packet = blockAndGetPacket(10);

            /* Check if we are interested in tracking data and parse it if we are. */
            if (trackingEnable) {
                if (packet != null) {
                    trackingOnline = true;
                    parsePacket(packet);
                } else {
                    trackingOnline = false;
                    DriverStation.reportWarning("Cannot get packet from JeVois Vision Processor", false);
                }
            } else {
                trackingOnline = false;
            }
        }
    }

    /*
     * Send the ping command to the JeVois to verify it is connected. Returns 0 on
     * success, -1 on unexpected response, -2 on timeout
     */
    public int sendPing() {
        if (visionPort != null) {
            return sendCmdAndCheck("ping");
        }
        return -1;
    }

    /*
     * Send commands to the JeVois to configure it for image-processing friendly
     * parameters
     */
    public void setCamVisionProcMode() {
        if (visionPort != null) {
            sendCmdAndCheck("setcam autoexp 1"); // Disable auto exposure
            sendCmdAndCheck("setcam absexp 50"); // Force exposure to a low value for vision processing
        }
    }

    /*
     * Send parameters to the camera to configure it for a human-readable image
     */
    public void setCamHumanDriverMode() {
        if (visionPort != null) {
            sendCmdAndCheck("setcam autoexp 0"); // Enable AutoExposure
        }
    }

    /*
     * Sends a command over serial to JeVois and returns immediately. Returns number
     * of bytes written
     */
    private int sendCmd(String cmd) {
        int bytes = visionPort.writeString(cmd + "\n");
        System.out.println("wrote " + bytes + "/" + (cmd.length() + 1) + " bytes, cmd: " + cmd);
        return bytes;
    }

    /*
     * Sends a command over serial to the JeVois, waits for a response, and checks
     * that response. Automatically ends the line termination character. Returns 0
     * if OK detected, -1 if ERR detected, -2 if timeout waiting for response.
     */
    public int sendCmdAndCheck(String cmd) {
        int retval = 0;
        sendCmd(cmd);
        retval = blockAndCheckForOK(1.0);

        if (retval == -1) {
            System.out.println(cmd + " Produced an error");
        } else if (retval == -2) {
            System.out.println(cmd + " timed out");
        }
        return retval;
    }

    /* Persistent but "local" variables for getBytesPeriodic() */
    private String getBytesWork = "";
    private int loopCount = 0;

    /*
     * Read bytes from the serial port in a non-blocking fashion. Will return the
     * whole thing once the first "OK" or "ERR" is seen in the stream. Returns null
     * if no string read back yet.
     */
    public String getCmdResponseNonBlock() {
        String retval = null;

        if (visionPort != null) {
            System.out.println("Has a port");
            if (visionPort.getBytesReceived() > 0) {
                String rxString = visionPort.readString();
                System.out.println("Waited: " + loopCount + " loops, Rcv'd: " + rxString);
                getBytesWork += rxString;
                if (getBytesWork.contains("OK") || getBytesWork.contains("ERR")) {
                    retval = getBytesWork;
                    getBytesWork = "";
                    System.out.println(retval);
                }
                loopCount = 0;
            } else {
                ++loopCount;
            }
        }
        return retval;
    }

    /*
     * Blocks thread execution till we get a response from the serial line or
     * timeout. Return values: 0 = OK in response -1 = ERR in response -2 = No token
     * found before timeout_s
     */
    public int blockAndCheckForOK(double timeout_s) {
        int retval = -2;
        double startTime = Timer.getFPGATimestamp();
        String testStr = "";

        if (visionPort != null) {
            while (Timer.getFPGATimestamp() - startTime < timeout_s) {
                if (visionPort.getBytesReceived() > 0) {
                    testStr += visionPort.readString();
                    if (testStr.contains("OK")) {
                        retval = 0;
                        break;
                    } else if (testStr.contains("ERR")) {
                        retval = -1;
                        break;
                    }
                } else {
                    sleep(10);
                }
            }
        }
        return retval;
    }

    /* buffer to contain data from the port while we gather full packets */
    private String packetBuffer = "";

    /*
     * Blocks thread execution till we get a valid packet from the serial line or
     * timeout. Return values: String = the packet null = No full packet found
     * before timeout_s
     */
    public String blockAndGetPacket(double timeout_s) {
        double startTime = Timer.getFPGATimestamp();

        if (visionPort != null) {
            int attempt = 0;
            while (Timer.getFPGATimestamp() - startTime < timeout_s) {
                attempt++;

                /* Keep trying to get bytes from the serial port until the timeout expires. */

                if (visionPort.getBytesReceived() > 0) {

                    /*
                     * If there are any bytes available, read them in and append them to the buffer.
                     */
                    packetBuffer += visionPort.readString();

                    /* Attempt to detect if the buffer currently contains a complete packet */
                    if (packetBuffer.contains(PACKET_START_CHAR)) {
                        if (packetBuffer.contains(PACKET_END_CHAR)) {
                            /*
                             * Buffer also contains at least one start & end character, But we don't know if
                             * they're in the right order yet. Start by getting the most-recent packet end
                             * character's index
                             */
                            int endIdx = packetBuffer.lastIndexOf(PACKET_END_CHAR);

                            /*
                             * Look for the index of the start character for the packet described by endIdx.
                             * Note this line of code assumes the start character for the packet must come
                             * before the end character.
                             */
                            int startIdx = packetBuffer.lastIndexOf(PACKET_START_CHAR, endIdx);

                            if (startIdx == -1) {
                                /*
                                 * If there was no start character before the end character, we can assume that
                                 * we have something a bit wacky in our buffer. For example: ",abc}garbage{1,2".
                                 * Since we've started to receive a good packet, discard everything prior to the
                                 * start character.
                                 */
                                startIdx = packetBuffer.lastIndexOf(PACKET_START_CHAR);
                                packetBuffer = packetBuffer.substring(startIdx);
                            } else {
                                /* Buffer contains a full packet. Extract it and clean up buffer */
                                String retval = packetBuffer.substring(startIdx, endIdx + 1);
                                packetBuffer = packetBuffer.substring(endIdx + 1);
                                return retval;
                            }
                        } else {
                            /*
                             * In this case, we have a start character, but no end to the buffer yet. Do
                             * nothing, just wait for more characters to come in.
                             */
                        }
                    } else {
                        /*
                         * Buffer contains no start characters. None of the current buffer contents can
                         * be meaningful. Discard the whole thing.
                         */
                        packetBuffer = "";
                    }
                } else {
                    sleep(10);
                }
            }
            System.out.println("Made " + attempt + " attempts to retrieve packet");
        } else {
            System.err.println("No port for reading packet");
        }
        return null;
    }

    /*
     * Private wrapper around the Thread.sleep method, to catch that interrupted
     * error.
     */
    private void sleep(int time_ms) {
        try {
            Thread.sleep(time_ms);
        } catch (InterruptedException e) {
            System.out.println("DO NOT WAKE THE SLEEPY BEAST");
            e.printStackTrace();
        }
    }

    /*
     * Mostly for debugging. Blocks execution while serOutEnable and just prints all
     * serial characters to the console. It might print a different message too if
     * nothing comes in.
     */
    public void blockAndPrintAllSerial() {
        if (visionPort != null) {
            System.out.println("Starting Serial Output Dipslay");
            while (debugOutEnable) {
                if (visionPort.getBytesReceived() > 0) {
                    System.out.print(visionPort.readString());
                } else {
                    sleep(1000);
                }
            }
        }
    }

    /*
     * Parse individual numbers from a packet
     */
    public void parsePacket(String packet) {
        JSONParser parser = new JSONParser();
        //System.out.println("Testing Parser: " + packet);

        try {
            Object obj = parser.parse(packet);
            JSONObject jsonObject = (JSONObject) obj;
            tx = (double) jsonObject.get("tx");
            ty = (double) jsonObject.get("ty");
            ta = (double) jsonObject.get("ta");
            tv = (boolean) jsonObject.get("tv");
            //conn = (boolean) jsonObject.get("conn");
        } catch (Exception e) {
            System.out.println(
                    "Parse Exception, probably need to check you JeVois output that it's matching what you are parsing");
            e.printStackTrace();
        }
    }

    /*
     * Main getters/setters
     */
    public double getTx() {
        return tx;
    }

    public double getTy() {
        return ty;
    }

    public double getTa() {
        return ta;
    }

    public boolean getTv() {
        return tv;
    }

    public boolean getValidConn() {
        return conn;
    }
    public double getCamMode() {
        return camMode;
    }

    public boolean isVisionOnline() {
        return trackingOnline;
    }

    /*
     * Sends command to JeVois and executes it
     */
    public static String executeCommand(String command) {
        StringBuffer output = new StringBuffer();

        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    /*
     * This thread runs a periodic task in the background to listen for vision
     * camera packets.
     */
    Thread jevoisListenerThread = new Thread(new Runnable() {
        public void run() {
            while (true) {
                backgroundUpdate();
            }
        }
    });

}