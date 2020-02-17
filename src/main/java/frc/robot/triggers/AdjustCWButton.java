package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the adjust cw launchpad button
 */
public class AdjustCWButton extends Trigger {

    /* Make this class public */
    public AdjustCWButton() {
      }

    /* Will return true when variable adjustCWButton in Launchpad is true */
    @Override
    public boolean get() {
      return Launchpad.adjustCWButton;
    }
  }