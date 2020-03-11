package frc.robot;

/*
 * This class contains all variables that can be edited in order to further configure and calibrate the robot. 
 * 
 * Author: Francisco Fabregat
 */
public class Config {

    /* Configuration for drivetrain speeds */
    public static double moveMinSpeed = 0.2;
    public static double turnMinSpeed = 0.2;
    public static double moveMaxSpeed = 1.0;
    public static double turnMaxSpeed = 0.95;
    public static double turnAccel = 0.075;
    public static double driveTargetAdjustSpeed = 0.3;
    public static double driveManualAdjustSpeed = 0.3;

    /* Configuration for color spinner speeds */
    public static double colorSpinSpeed = 0.2;
    public static double colorAdjustSpeed = 0.1;
    public static double colorRaiseSpeed = 0.5;
    public static double colorColorSpeed = 0.05;
 
    /* Configuration for intake speed */
    public static double intakeSpeed = 0.7;
    public static double intakeSensorDist = 5.0;
    public static double intakeCounter = 60.0;
 
    /* Configuration for ball transport speeds */
    public static double ballTransportSpeed = 0.3;
    public static double ballTransportBackSpeed = 0.3;
    public static double ballSensorDist = 3.0;

    /* Configuration for shooter control speeds */
    public static double controlShootSpeed = 0.3;
    public static double controlReverseSpeed = 0.3;
    public static double controlKeepInSpeed = 0.1;

    /* Configuration for climber speeds */
    public static double climbSpeed = 0.3;
    public static double climbAdjustSpeed = 0.3;

    /* Shooting configurations */
    public static double maxShootRPM = 3600.0;
    public static double shootTurnTolerance = 2.0;
    public static double shootRPMTolerance = 20.0;
    public static double defaultRevSpeed = 0.37;

    /* Shooting parabola variables */
    //public static double shootRPMA = 0.106487;
    //public static double shootRPMB = -15.235;
    //public static double shootRPMC = 1824.18;
    public static double shootRPMA = 0.0216441;
    public static double shootRPMB = -6.95135;
    public static double shootRPMC = 1974.3;

    public static double shootPercentageM = 0.0233652;
    public static double shootPercentageB = -0.858592;

    /* Limelight variables */
    public static double limelightHeight = 19.875; //inches
    public static double targetHeight = 98.19; //inches
    public static double limelightAngle = 24.0; //Degrees

    /* Define robot width */
    public static double robotWidth = 0.0;
}