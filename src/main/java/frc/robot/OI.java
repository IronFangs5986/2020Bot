package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/*
 * This class defines everything related to joysticks and controls
 */
public class OI {

  /* Define joysticks and controllers */
  public static Joystick driver;
  public static Joystick operator;

  /* Define buttons */
  

  /* Allows buttons and joysticks to be accessed from anywhere */
  public OI() {

    /* Assign joystick id to joystick */
    driver = new Joystick(1);
    operator = new Joystick(2);

    /* Assign button id to buttons */
    

    /* Handle button presses */
    
  }
}