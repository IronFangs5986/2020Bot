package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the trigger for the auto color launchpad button
 */
public class AutoColorButton extends Trigger {

    /* Make this class public */
    public AutoColorButton() {
      }

    /* Will return true when variable autoCoorButton in Launchpad is true */
    @Override
    public boolean get() {
      return Launchpad.autoColorButton;
    }
  }