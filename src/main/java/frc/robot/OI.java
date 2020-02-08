package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AdjustDisk;
import frc.robot.commands.Shoot;
import frc.robot.commands.SpinToColor;
import frc.robot.triggers.BlueButton;
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

  /* Allows buttons and joysticks to be accessed from anywhere */
  public OI() {

    /* Assign joystick id to joystick */
    driver = new Joystick(1);

    /* Assign button id to buttons */
    testColor = new JoystickButton(driver, 1);
    adjDiskCW = new JoystickButton(driver, 7);
    adjDiskCCW = new JoystickButton(driver, 8);
    shoot = new JoystickButton(driver, 2);

    /* Handle button presses */
    testColor.whenPressed(new SpinToColor(4));
    adjDiskCW.whenPressed(new AdjustDisk(true));
    adjDiskCCW.whenPressed(new AdjustDisk(false));
    shoot.whenPressed(new Shoot());

    /* Handle launchpad triggers */
    new RedButton().whenActive(new SpinToColor(1));
    new GreenButton().whenActive(new SpinToColor(2));
    new BlueButton().whenActive(new SpinToColor(3));
    new YellowButton().whenActive(new SpinToColor(4));
  }
}