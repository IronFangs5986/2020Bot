package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.util.Color;

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

  /* Initialize SpeedControllerGroups for DifferentialDrive */
  public static SpeedControllerGroup leftGroup;
  public static SpeedControllerGroup rightGroup;

  /* Initialize DifferentialDrive */
  public static DifferentialDrive robotDrive;

  /* Initialize motors */
  public static WPI_VictorSPX spinnerMotor;
  public static CANSparkMax intakeMotor;
  public static CANSparkMax indexMotor;
  public static CANSparkMax transportMotor;
  public static CANSparkMax shooterMotor;
  public static CANSparkMax climbMotor;

  /* Initialize encoders */
  public static CANEncoder shooterEncoder;

  /* Initialize I2C port */
  public static I2C.Port i2cPort;

  /* Initialize Color Sensor */
  public static ColorSensorV3 colorSensor;

  /* Initialize Color Sensor ColorMatch */
  public static ColorMatch colorMatch;

  /* Initialize Color Sensor color targets */
  public static Color BlueTarget;
  public static Color GreenTarget;
  public static Color RedTarget;
  public static Color YellowTarget;

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

    /* Define drive Sparks with CAN id */
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

    /* Define Victors with CAN id */
    spinnerMotor = new WPI_VictorSPX(7);
    intakeMotor = new CANSparkMax(8, MotorType.kBrushless);
    indexMotor = new CANSparkMax(9, MotorType.kBrushless);
    transportMotor = new CANSparkMax(10, MotorType.kBrushless);
    shooterMotor = new CANSparkMax(11, MotorType.kBrushless);
    climbMotor = new CANSparkMax(12, MotorType.kBrushless);
    
    /* Define encoders*/
    shooterEncoder =  shooterMotor.getEncoder();

    /* Define the I2C port */
    i2cPort = I2C.Port.kOnboard;

    /* Define the Color Sensor as in the I2C port */
    colorSensor = new ColorSensorV3(i2cPort);
    
    /* Define ColorMatch */
    colorMatch = new ColorMatch();

    /* Define ColorMatch colors */
    BlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    GreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    RedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    YellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    /* Define and start camera server */
    //UsbCamera server = CameraServer.getInstance().startAutomaticCapture(0);
    //server.setResolution(160, 120);
    //server.setFPS(30);

    /* Define robot information */
    robotWidth = 0.0; // Unknown
    robotLength = 0.0; // Unknown
    robotHeight = 0.0; // Unknown
    minDriveSpeed = 0.0; // Unknown
  }
}