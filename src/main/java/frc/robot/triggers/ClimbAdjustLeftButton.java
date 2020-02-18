package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the climb adjust left launchpad button
 */
public class ClimbAdjustLeftButton extends Trigger {

    /* Make this class public */
    public ClimbAdjustLeftButton() {
      }

    /* Will return true when variable climbAdjustLeftButton in Launchpad is true */
    @Override
    public boolean get() {
      return Launchpad.climbAdjustLeftButton;
    }
  }