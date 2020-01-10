package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the blue launchpad button
 */
public class BlueButton extends Trigger {

    /* Make this class public */
    public BlueButton() {
      }

    /* Will return true when variable blueButton in Launchpad is true*/
    @Override
    public boolean get() {
      return Launchpad.blueButton;
    }
  }