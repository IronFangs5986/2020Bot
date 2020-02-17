package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the adjust ccw launchpad button
 */
public class AdjustCCWButton extends Trigger {

    /* Make this class public */
    public AdjustCCWButton() {
      }

    /* Will return true when variable adjustCCWButton in Launchpad is true */
    @Override
    public boolean get() {
      return Launchpad.adjustCCWButton;
    }
  }