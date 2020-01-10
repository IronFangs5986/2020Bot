package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the red launchpad button
 */
public class RedButton extends Trigger {

    /* Make this class public */
    public RedButton() {
      }

    /* Will return true when variable redButton in Launchpad is true*/
    @Override
    public boolean get() {
      return Launchpad.redButton;
    }
  }