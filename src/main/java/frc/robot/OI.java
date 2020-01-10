package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
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
  

  /* Allows buttons and joysticks to be accessed from anywhere */
  public OI() {

    /* Assign joystick id to joystick */
    driver = new Joystick(1);

    /* Assign button id to buttons */
    

    /* Handle button presses */


    /* Handle launchpad triggers */
    new RedButton().whenActive(new SpinToColor(1));
    new GreenButton().whenActive(new SpinToColor(2));
    new BlueButton().whenActive(new SpinToColor(3));
    new YellowButton().whenActive(new SpinToColor(4));
  }
}