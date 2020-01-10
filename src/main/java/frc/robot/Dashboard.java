package frc.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/*
 * This class handles all NetworkTable actions that communicate with the Dashboard
 */
public class Dashboard {

    /* Initialize NetworkTables instance */
    NetworkTableInstance inst = NetworkTableInstance.getDefault();

    /* Fetch table 'FangsDashboard' from NetworkTables */
    NetworkTable table = inst.getTable("FangsDashboard");

    /* Define NetworkTable entries */
    NetworkTableEntry battery = table.getEntry("battery");
    NetworkTableEntry selectedAuto = table.getEntry("autoSelected");
    NetworkTableEntry gyroX = table.getEntry("Gyro-X");
    NetworkTableEntry gyroY = table.getEntry("Gyro-Y");
    NetworkTableEntry gyroZ = table.getEntry("Gyro-Z");
    NetworkTableEntry autoList = table.getEntry("AutoList");
    NetworkTableEntry robotMode = table.getEntry("RobotMode");
    NetworkTableEntry compState = table.getEntry("CompressorState");
    NetworkTableEntry cameraActive = table.getEntry("CameraActive");
    NetworkTableEntry vision = table.getEntry("vision");
    NetworkTableEntry time = table.getEntry("time");

    /* Send battery voltage to NetworkTables */
    public void setBattery(final Double voltage) {
        battery.setDouble(voltage);
    }

    /* Get the current selected autonomous mode from NetworkTables */
    public int getSelectedAutonomous() {
        return (int) selectedAuto.getDouble(0.0);
    }

    /* Set an autonomous mode in NetworkTables */
    public void setAutonomous(final int mode) {
        selectedAuto.setNumber(mode);
    }

    /* Send gyroscope values to NetworkTables */
    public void setGyroscope(final Double x, final Double y, final Double z) {
        gyroX.setDouble(x);
        gyroY.setDouble(y);
        gyroZ.setDouble(z);
    }

    /* Send the list of all autonomous modes to NetworkTables */
    public void setAutonomousList(final String[] list) {
        autoList.setStringArray(list);
    }

    /* Send the current robot mode to NetworkTables (Disabled, Teleop, Autonomous)*/
    public void setRobotMode(final String mode) {
        robotMode.setString(mode);
    }

    /* Send the compressor state to NetworkTables */
    public void setCompressorState(final Boolean enabled) {
        compState.setBoolean(enabled);
    }

    /* Send the compressor state to NetworkTables */
    public void setCameraTrackingStatus(final Boolean active) {
        cameraActive.setBoolean(active);
    }

    /* Send vision data to NetworkTables */
    public void setVision(final Double data) {
        vision.setDouble(data);
    }

    /* Send time to NetworkTables */
    public void setTime(final double seconds) {
        if (seconds == -1.0) {
            time.setString("2:15");
        } else {
        final int minutes = (int) (seconds / 60);
        final int secondsRemaining = (int) (seconds - (minutes * 60));
        time.setString(minutes+":"+secondsRemaining);
        }
    }

}