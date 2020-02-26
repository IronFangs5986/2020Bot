package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.AdjustClimb;
import frc.robot.commands.AdjustDisk;
import frc.robot.commands.AdjustRevSpeed;
import frc.robot.commands.Climb;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeBalls;
import frc.robot.commands.ManualShoot;
import frc.robot.commands.MoveSpinner;
import frc.robot.commands.OnlyShoot;
import frc.robot.commands.RawControl;
import frc.robot.commands.RawIntake;
import frc.robot.commands.RawTransport;
import frc.robot.commands.ReverseBalls;
import frc.robot.commands.RotationControl;
import frc.robot.commands.Shoot;
import frc.robot.commands.SpinToColor;
import frc.robot.commands.TrackingLight;
import frc.robot.triggers.LaunchpadButton;

/*
 * This class defines everything related to joysticks and controls
 */
public class OI {

  /* Define joysticks and controllers */
  public static Joystick driver;

  /* Define buttons */
  public static JoystickButton shoot;
  public static JoystickButton intake;
  public static JoystickButton reverse;
  public static JoystickButton rawShoot;

  /* Define triggers */
  /*public static Trigger redButton = new RedButton();
  public static Trigger greenButton = new GreenButton();
  public static Trigger blueButton = new BlueButton();
  public static Trigger yellowButton = new YellowButton();
  public static Trigger climbButton = new ClimbButton();
  public static Trigger climbAdjustRightButton = new ClimbAdjustRightButton();
  public static Trigger climbAdjustLeftButton = new ClimbAdjustLeftButton();
  public static Trigger autoColorButton = new AutoColorButton();
  public static Trigger spinButton = new SpinButton();
  public static Trigger spinnerUpButton = new SpinnerUpButton();
  public static Trigger spinnerDownButton = new SpinnerDownButton();
  public static Trigger adjustCWButton = new AdjustCWButton();
  public static Trigger adjustCCWButton = new AdjustCCWButton();*/

  public static Trigger redButton = new LaunchpadButton(1);
  public static Trigger greenButton = new LaunchpadButton(2);
  public static Trigger blueButton = new LaunchpadButton(3);
  public static Trigger yellowButton = new LaunchpadButton(4);
  public static Trigger climbButton = new LaunchpadButton(6);
  public static Trigger climbAdjustRightButton = new LaunchpadButton(15);
  public static Trigger climbAdjustLeftButton = new LaunchpadButton(14);
  public static Trigger autoColorButton = new LaunchpadButton(10);
  public static Trigger spinButton = new LaunchpadButton(5);
  public static Trigger spinnerUpButton = new LaunchpadButton(8);
  public static Trigger spinnerDownButton = new LaunchpadButton(9);
  public static Trigger adjustCWButton = new LaunchpadButton(11);
  public static Trigger adjustCCWButton = new LaunchpadButton(12);
  public static Trigger autoShootButton = new LaunchpadButton(17);
  public static Trigger semiAutoShootButton = new LaunchpadButton(18);
  public static Trigger semiAutoRevButton = new LaunchpadButton(19);
  public static Trigger intakeOutButton = new LaunchpadButton(20);
  public static Trigger intakeInButton = new LaunchpadButton(21);
  public static Trigger indexerOutButton = new LaunchpadButton(22);
  public static Trigger indexerInButton = new LaunchpadButton(23);
  public static Trigger transportOutButton = new LaunchpadButton(24);
  public static Trigger transportInButton = new LaunchpadButton(25);
  public static Trigger controlOutButton = new LaunchpadButton(26);
  public static Trigger controlInButton = new LaunchpadButton(27);
  public static Trigger manualShootButton = new LaunchpadButton(28);
  public static Trigger visionOnButton = new LaunchpadButton(16);
  public static Trigger robotSpinLeftButton = new LaunchpadButton(29);
  public static Trigger robotSpinRightButton = new LaunchpadButton(30);
  public static Trigger autoIntakeButton = new LaunchpadButton(31);
  public static Trigger revDownButton = new LaunchpadButton(32);
  public static Trigger revUpButton = new LaunchpadButton(33);
  
  /* Allows buttons and joysticks to be accessed from anywhere */
  public OI() {

    /* Assign joystick id to joystick */
    driver = new Joystick(1);

    /* Assign button id to buttons */
    shoot = new JoystickButton(driver, 2);
    intake = new JoystickButton(driver, 3);
    reverse = new JoystickButton(driver, 4);
    //rawShoot = new JoystickButton(driver, 11);

    /* Handle button presses */
    shoot.whenPressed(new Shoot(false));
    intake.whenPressed(new IntakeBalls());
    reverse.whenPressed(new ReverseBalls());
    //rawShoot.whenPressed(new ManualShoot(false));

    /* Handle launchpad triggers */
    redButton.whenActive(new SpinToColor(1));
    greenButton.whenActive(new SpinToColor(2));
    blueButton.whenActive(new SpinToColor(3));
    yellowButton.whenActive(new SpinToColor(4));
    climbButton.whenActive(new Climb());
    climbAdjustRightButton.whenActive(new AdjustClimb(true));
    climbAdjustLeftButton.whenActive(new AdjustClimb(false));
    autoColorButton.whenActive(new SpinToColor(5));
    spinButton.whenActive(new RotationControl());
    spinnerUpButton.whenActive(new MoveSpinner(true));
    spinnerDownButton.whenActive(new MoveSpinner(false));
    adjustCWButton.whenActive(new AdjustDisk(true));
    adjustCCWButton.whenActive(new AdjustDisk(false));
    autoShootButton.whenActive(new Shoot(true));
    semiAutoRevButton.whenActive(new ManualShoot(true));
    intakeOutButton.whenActive(new RawIntake(false));
    intakeInButton.whenActive(new RawIntake(true));
    transportOutButton.whenActive(new RawTransport(false));
    transportInButton.whenActive(new RawTransport(true));
    controlOutButton.whenActive(new RawControl(false));
    controlInButton.whenActive(new RawControl(true));
    manualShootButton.whenActive(new OnlyShoot());
    visionOnButton.whenActive(new TrackingLight(true));
    autoIntakeButton.whenActive(new Intake());
    //revDownButton.whenActive(new AdjustRevSpeed(false));
    //revUpButton.whenActive(new AdjustRevSpeed(true));

    visionOnButton.whenInactive(new TrackingLight(false));
  }
}