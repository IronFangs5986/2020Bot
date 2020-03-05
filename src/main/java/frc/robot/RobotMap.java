package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
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
  public static CANSparkMax spinnerMotor;
  public static CANSparkMax intakeMotor;
  public static CANSparkMax indexMotor;
  public static CANSparkMax transportMotor;
  public static CANSparkMax shooterMotor;
  public static CANSparkMax climbMotor;
  public static CANSparkMax adjustMotor;
  public static CANSparkMax controlMotor;
  public static CANSparkMax raiseSpinnerMotor;

  /* Initialize encoders */
  public static CANEncoder shooterEncoder;
  public static CANEncoder spinnerEncoder;
  public static CANEncoder leftDriveEncoder;
  public static CANEncoder rightDriveEncoder;

  public static CANPIDController shooterPIDController;

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

  public static Solenoid trackingLight;
  
  /* Initialize gyroscope */
  public static ADIS16448_IMU gyro;

  /* Initialize untrasonic sensors */
  public static Ultrasonic firstBallUltra;
  public static Ultrasonic secondBallUltra;
  public static Ultrasonic thirdBallUltra;
  public static Ultrasonic fourthBallUltra;
  public static Ultrasonic fifthBallUltra;

  public static AnalogInput distanceSensor;

  /* Initialize camera and camera server variables */
  public static UsbCamera driverCamera = null;
  public static MjpegServer driverCameraServer = null;

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


    FrontLeftMotor.setOpenLoopRampRate(0.1);
    MiddleLeftMotor.setOpenLoopRampRate(0.1);
    BackLeftMotor.setOpenLoopRampRate(0.1);
    FrontRightMotor.setOpenLoopRampRate(0.1);
    MiddleRightMotor.setOpenLoopRampRate(0.1);
    BackRightMotor.setOpenLoopRampRate(0.1);
    
    /* Define SpeedControllerGroups for DifferentialDrive */
    leftGroup = new SpeedControllerGroup(FrontLeftMotor, MiddleLeftMotor, BackLeftMotor);
    rightGroup = new SpeedControllerGroup(FrontRightMotor, MiddleRightMotor, BackRightMotor);

    /* Define robotDrive as a DifferentialDrive for drivetrain */
    robotDrive = new DifferentialDrive(leftGroup, rightGroup);

    /* Define SPARK MAX with CAN id */
    spinnerMotor = new CANSparkMax(7, MotorType.kBrushless);
    intakeMotor = new CANSparkMax(8, MotorType.kBrushless);
    indexMotor = new CANSparkMax(9, MotorType.kBrushless);
    transportMotor = new CANSparkMax(10, MotorType.kBrushless);
    shooterMotor = new CANSparkMax(11, MotorType.kBrushless);
    climbMotor = new CANSparkMax(12, MotorType.kBrushless);
    adjustMotor = new CANSparkMax(14, MotorType.kBrushless);
    controlMotor = new CANSparkMax(15, MotorType.kBrushless);
    raiseSpinnerMotor = new CANSparkMax(16, MotorType.kBrushed);

    /* Motor Settings */
    spinnerMotor.setInverted(true);
    shooterMotor.setInverted(true);
    controlMotor.setInverted(true);
    raiseSpinnerMotor.setInverted(true);

    /* Define encoders */
    shooterEncoder =  shooterMotor.getEncoder();
    spinnerEncoder = spinnerMotor.getEncoder();
    leftDriveEncoder = MiddleLeftMotor.getEncoder();
    rightDriveEncoder = MiddleRightMotor.getEncoder();

    /* Define PID controllers */
    shooterPIDController = shooterMotor.getPIDController();
    /*shooterPIDController.setP(0.1);
    shooterPIDController.setI(1e-4);
    shooterPIDController.setD(1);
    shooterPIDController.setIZone(0);
    shooterPIDController.setFF(0);*/
    
    /*shooterPIDController.setP(5e-5);
    shooterPIDController.setI(1e-6);
    shooterPIDController.setD(0);
    shooterPIDController.setIZone(0);
    shooterPIDController.setFF(0.000156);*/

    /* Configure PID controllers */
    shooterPIDController.setP(0.000009);//0.000009
    shooterPIDController.setI(0);
    //shooterPIDController.setFF(0.000241);
    shooterPIDController.setFF(0.000214); //227
    shooterPIDController.setOutputRange(-1, 1);

    //0.000214

    /* Define the I2C port */
    i2cPort = I2C.Port.kOnboard;

    /* Define the Color Sensor as in the I2C port */
    colorSensor = new ColorSensorV3(i2cPort);
    
    /* Define ColorMatch */
    colorMatch = new ColorMatch();

    /* Define ColorMatch colors */
    //BlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    //GreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    //RedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    //YellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    BlueTarget = ColorMatch.makeColor(0.420, 1.390, 1.490); //3
    GreenTarget = ColorMatch.makeColor(0.412, 1.350, 0.603); //2
    RedTarget = ColorMatch.makeColor(1.050, 0.790, 0.321); //1
    YellowTarget = ColorMatch.makeColor(1.600, 2.850, 0.670); //4

    trackingLight = new Solenoid(0);

    /* Define gyroscope class */
    gyro = new ADIS16448_IMU();

    /* Define and configure ultrasonic sensors */
    firstBallUltra = new Ultrasonic(0, 1);
    secondBallUltra = new Ultrasonic(2, 3);
    thirdBallUltra = new Ultrasonic(4, 5);
    fourthBallUltra = new Ultrasonic(6, 7);
    fifthBallUltra = new Ultrasonic(8, 9);

    firstBallUltra.setAutomaticMode(true);
    secondBallUltra.setAutomaticMode(true);
    thirdBallUltra.setAutomaticMode(true);
    fourthBallUltra.setAutomaticMode(true);
    fifthBallUltra.setAutomaticMode(true);

    distanceSensor = new AnalogInput(0);
    
    /* Define and start camera server */
    UsbCamera server = CameraServer.getInstance().startAutomaticCapture(0);
    server.setResolution(160, 120);
    server.setFPS(30);
  }
}