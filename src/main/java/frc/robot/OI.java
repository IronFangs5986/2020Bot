package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.AdjustDisk;
import frc.robot.commands.Climb;
import frc.robot.commands.IntakeBalls;
import frc.robot.commands.Shoot;
import frc.robot.commands.SpinToColor;
import frc.robot.triggers.BlueButton;
import frc.robot.triggers.ClimbButton;
import frc.robot.triggers.GreenButton;
import frc.robot.triggers.RedButton;
import frc.robot.triggers.YellowButton;

/*
 * This class defines everything related to joysticks and controls
 */
public class OI {

  /* Define joysticks and controllers */
  public static Joystick driver;

  /* Define buttons */
  public static JoystickButton testColor;
  public static JoystickButton adjDiskCW;
  public static JoystickButton adjDiskCCW;
  public static JoystickButton shoot;
  public static JoystickButton intake;

  /* Define triggers */
  public static Trigger redButton = new RedButton();
  public static Trigger greenButton = new GreenButton();
  public static Trigger blueButton = new BlueButton();
  public static Trigger yellowButton = new YellowButton();
  public static Trigger climbButton = new ClimbButton();

  /* Allows buttons and joysticks to be accessed from anywhere */
  public OI() {

    /* Assign joystick id to joystick */
    driver = new Joystick(1);

    /* Assign button id to buttons */
    testColor = new JoystickButton(driver, 1);
    adjDiskCW = new JoystickButton(driver, 7);
    adjDiskCCW = new JoystickButton(driver, 8);
    shoot = new JoystickButton(driver, 2);
    intake = new JoystickButton(driver, 3);

    /* Handle button presses */
    testColor.whenPressed(new SpinToColor(4));
    adjDiskCW.whenPressed(new AdjustDisk(true));
    adjDiskCCW.whenPressed(new AdjustDisk(false));
    shoot.whenPressed(new Shoot());
    intake.whenPressed(new IntakeBalls());

    /* Handle launchpad triggers */
    redButton.whenActive(new SpinToColor(1));
    greenButton.whenActive(new SpinToColor(2));
    blueButton.whenActive(new SpinToColor(3));
    yellowButton.whenActive(new SpinToColor(4));
    climbButton.whenActive(new Climb());
  }
}