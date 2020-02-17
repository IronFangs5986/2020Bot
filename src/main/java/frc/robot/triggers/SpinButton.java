package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the spin launchpad button
 */
public class SpinButton extends Trigger {

    /* Make this class public */
    public SpinButton() {
      }

    /* Will return true when variable spinButton in Launchpad is true */
    @Override
    public boolean get() {
      return Launchpad.spinButton;
    }
  }