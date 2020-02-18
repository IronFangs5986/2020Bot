package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the climb adjust right launchpad button
 */
public class ClimbAdjustRightButton extends Trigger {

    /* Make this class public */
    public ClimbAdjustRightButton() {
      }

    /* Will return true when variable climbAdjustRightButton in Launchpad is true */
    @Override
    public boolean get() {
      return Launchpad.climbAdjustRightButton;
    }
  }