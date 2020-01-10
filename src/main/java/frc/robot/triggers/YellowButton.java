package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the yellow launchpad button
 */
public class YellowButton extends Trigger {

    /* Make this class public */
    public YellowButton() {
      }

    /* Will return true when variable yellowButton in Launchpad is true*/
    @Override
    public boolean get() {
      return Launchpad.yellowButton;
    }
  }