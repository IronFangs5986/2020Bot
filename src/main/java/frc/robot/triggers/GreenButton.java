package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the green launchpad button
 */
public class GreenButton extends Trigger {

    /* Make this class public */
    public GreenButton() {
      }

    /* Will return true when variable greenButton in Launchpad is true*/
    @Override
    public boolean get() {
      return Launchpad.greenButton;
    }
  }