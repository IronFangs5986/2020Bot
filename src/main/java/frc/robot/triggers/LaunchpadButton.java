package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Launchpad;

/*
 * This class handles the triggers for launchpad buttons
 * 
 * Author: Francisco Fabregat
 */
public class LaunchpadButton extends Trigger {

    /* Initialize buttonId to be set with the given button id */
    int buttonId;

    /* Make this class public */
    public LaunchpadButton(int id) {
        buttonId = id;
    }

    /* Will return true when variables in Launchpad are true */
    @Override
    public boolean get() {
        if (buttonId == 1) {
            return Launchpad.redButton;
        } else if (buttonId == 2) {
            return Launchpad.greenButton;
        } else if (buttonId == 3) {
            return Launchpad.blueButton;
        } else if (buttonId == 4) {
            return Launchpad.yellowButton;
        } else if (buttonId == 5) {
            return Launchpad.spinButton;
        } else if (buttonId == 6) {
            return Launchpad.climbButton1 && Launchpad.climbButton2;
        } else if (buttonId == 7) {
            return false;
        } else if (buttonId == 8) {
            return Launchpad.spinnerUpButton;
        } else if (buttonId == 9) {
            return Launchpad.spinnerDownButton;
        } else if (buttonId == 10) {
            return Launchpad.autoColorButton;
        } else if (buttonId == 11) {
            return  Launchpad.adjustCWButton;
        } else if (buttonId == 12) {
            return Launchpad.adjustCCWButton;
        } else if (buttonId == 14) {
            return Launchpad.climbAdjustLeftButton;
        } else if (buttonId == 15) {
            return Launchpad.climbAdjustRightButton;
        } else if (buttonId == 16) {
            return Launchpad.visionOnButton;
        } else if (buttonId == 17) {
            return Launchpad.autoShootButton;
        } else if (buttonId == 18) {
            return Launchpad.semiAutoShootButton;
        } else if (buttonId == 19) {
            return Launchpad.semiAutoRevButton;
        } else if (buttonId == 20) {
            return Launchpad.intakeOutButton;
        } else if (buttonId == 21) {
            return Launchpad.intakeInButton;
        } else if (buttonId == 22) {
            return Launchpad.indexerOutButton;
        } else if (buttonId == 23) {
            return Launchpad.indexerInButton;
        } else if (buttonId == 24) {
            return Launchpad.transportOutButton;
        } else if (buttonId == 25) {
            return Launchpad.transportInButton;
        } else if (buttonId == 26) {
            return Launchpad.controlOutButton;
        } else if (buttonId == 27) {
            return Launchpad.controlInButton;
        } else if (buttonId == 28) {
            return Launchpad.manualShootButton;
        } else if (buttonId == 29) {
            return Launchpad.robotSpinLeftButton;
        } else if (buttonId == 30) {
            return Launchpad.robotSpinRightButton;
        } else if (buttonId == 31) {
            return Launchpad.autoIntakeButton;
        } else if (buttonId == 32) {
            return Launchpad.revDownButton;
        } else if (buttonId == 33) {
            return Launchpad.revUpButton;
        } else if (buttonId == 34) {
            return Launchpad.policeLightsButton;
        } else if (buttonId == 35) {
            return Launchpad.autoAlignButton;
        } else {
            return false;
        }
    }
  }