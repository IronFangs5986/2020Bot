package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that moves the disc to a specific color
 */
public class AdjustDisk extends Command {

    /* Variables set when calling the command */
    boolean moveClockwise;

    public AdjustDisk(Boolean clockwise) {
        /* Require the spinner subsystem */
        requires(Robot.colorSpinner);
        moveClockwise = clockwise;
    }

    /*
     * Executes the command
     */
    protected void execute() {

        /*
         * Moves the spinner clockwise or counterclockwise for shortest distance if possible, and executes the right
         * command
         */
            Robot.colorSpinner.adjustDisk(moveClockwise);
    }

    @Override
    protected boolean isFinished() {
        if (moveClockwise) {
            if (OI.driver.getRawButton(7) == false) {
                return true;
            } else {
                return false;
            }
        } else {
            if (OI.driver.getRawButton(8) == false) {
                return true;
            } else {
                return false;
            }
        }
    }

     /*
     * Sets the spinner to stop once the command is finished
     */
    protected void end() {
        Robot.colorSpinner.stopDisc();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}