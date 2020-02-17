package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.AdjustDisk;
import frc.robot.commands.Climb;
import frc.robot.commands.IntakeBalls;
import frc.robot.commands.ManualShoot;
import frc.robot.commands.ReverseBalls;
import frc.robot.commands.Shoot;
import frc.robot.commands.SpinToColor;
import frc.robot.triggers.AdjustCCWButton;
import frc.robot.triggers.AdjustCWButton;
import frc.robot.triggers.AutoColorButton;
import frc.robot.triggers.BlueButton;
import frc.robot.triggers.ClimbButton;
import frc.robot.triggers.GreenButton;
import frc.robot.triggers.RedButton;
import frc.robot.triggers.SpinButton;
import frc.robot.triggers.YellowButton;

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
  public static Trigger redButton = new RedButton();
  public static Trigger greenButton = new GreenButton();
  public static Trigger blueButton = new BlueButton();
  public static Trigger yellowButton = new YellowButton();
  public static Trigger climbButton = new ClimbButton();
  public static Trigger autoColorButton = new AutoColorButton();
  public static Trigger spinButton = new SpinButton();
  public static Trigger adjustCWButton = new AdjustCWButton();
  public static Trigger adjustCCWButton = new AdjustCCWButton();

  /* Allows buttons and joysticks to be accessed from anywhere */
  public OI() {

    /* Assign joystick id to joystick */
    driver = new Joystick(1);

    /* Assign button id to buttons */
    shoot = new JoystickButton(driver, 2);
    intake = new JoystickButton(driver, 3);
    reverse = new JoystickButton(driver, 4);
    rawShoot = new JoystickButton(driver, 11);

    /* Handle button presses */
    shoot.whenPressed(new Shoot());
    intake.whenPressed(new IntakeBalls());
    reverse.whenPressed(new ReverseBalls());
    rawShoot.whenPressed(new ManualShoot());

    /* Handle launchpad triggers */
    redButton.whenActive(new SpinToColor(1));
    greenButton.whenActive(new SpinToColor(2));
    blueButton.whenActive(new SpinToColor(3));
    yellowButton.whenActive(new SpinToColor(4));
    climbButton.whenActive(new Climb());
    autoColorButton.whenActive(new SpinToColor(5));
    //spinButton.whenActive(new RotationControl());
    adjustCWButton.whenActive(new AdjustDisk(true));
    adjustCCWButton.whenActive(new AdjustDisk(false));
  }
}