package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the spinner down launchpad button
 */
public class SpinnerDownButton extends Trigger {

    /* Make this class public */
    public SpinnerDownButton() {
      }

    /* Will return true when variable spinnerDownButton in Launchpad is true */
    @Override
    public boolean get() {
      return Launchpad.spinnerDownButton;
    }
  }