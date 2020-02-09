package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the climb buttons
 */
public class ClimbButton extends Trigger {

    /* Make this class public */
    public ClimbButton() {}

    /* Will return true when both climb button variables in Launchpad are true */
    @Override
    public boolean get() {
      return Launchpad.climbButton1 && Launchpad.climbButton2;
    }
  }
