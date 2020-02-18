package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the spinner up launchpad button
 */
public class SpinnerUpButton extends Trigger {

    /* Make this class public */
    public SpinnerUpButton() {
      }

    /* Will return true when variable spinnerUpButton in Launchpad is true */
    @Override
    public boolean get() {
      return Launchpad.spinnerUpButton;
    }
  }