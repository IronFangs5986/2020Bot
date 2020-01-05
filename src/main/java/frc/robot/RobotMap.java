package frc.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/*
 * All universal variables and robot components are found here
 */
public class RobotMap {

  /* Initialize drive Spark variables */
  public static CANSparkMax FrontLeftMotor;
  public static CANSparkMax MiddleLeftMotor;
  public static CANSparkMax BackLeftMotor;
  public static CANSparkMax FrontRightMotor;
  public static CANSparkMax MiddleRightMotor;
  public static CANSparkMax BackRightMotor;

  /* Initialize DifferentialDrive */
  public static DifferentialDrive robotDrive;

  /* Initialize SpeedControllerGroups for DifferentialDrive */
  public static SpeedControllerGroup leftGroup;
  public static SpeedControllerGroup rightGroup;

  /* Initialize compressor */
  public static Compressor compressor;

  /* Initialize solenoids */


  /* Initialize gyroscope */
  public static ADIS16448_IMU gyro;

  /* Initialize camera and camera server variables */
  public static UsbCamera driverCamera = null;
  public static MjpegServer driverCameraServer = null;

  /* Initialize robot information */
  public static Double robotWidth;
  public static Double robotLength;
  public static Double robotHeight;
  public static Double minDriveSpeed;

  /*
   * Initialize all components
   */
  public static void init() {

    /* Define drive Victors with CAN id */
    FrontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
    MiddleLeftMotor = new CANSparkMax(2, MotorType.kBrushless);
    BackLeftMotor = new CANSparkMax(3, MotorType.kBrushless);
    FrontRightMotor = new CANSparkMax(4, MotorType.kBrushless);
    MiddleRightMotor = new CANSparkMax(5, MotorType.kBrushless);
    BackRightMotor = new CANSparkMax(6, MotorType.kBrushless);

    /* Define SpeedControllerGroups for DifferentialDrive */
    leftGroup = new SpeedControllerGroup(FrontLeftMotor, MiddleLeftMotor, BackLeftMotor);
    rightGroup = new SpeedControllerGroup(FrontRightMotor, MiddleRightMotor, BackRightMotor);

    /* Define robotDrive as a DifferentialDrive for drivetrain */
    robotDrive = new DifferentialDrive(leftGroup, rightGroup);

    /* Define compressor */
    compressor = new Compressor(0);

    /* Define solenoids */
    

    /* Define gyroscope class */
    gyro = new ADIS16448_IMU();

    /* Define and start camera server */
    UsbCamera server = CameraServer.getInstance().startAutomaticCapture(0);
    server.setResolution(160, 120);
    server.setFPS(30);

    /* Define robot information */
    robotWidth = 0.0; // Unknown
    robotLength = 0.0; // Unknown
    robotHeight = 0.0; // Unknown
    minDriveSpeed = 0.0; // Unknown
  }
}