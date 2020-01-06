package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/*
 * This is the command that moves the disc to a specific color
 */
public class SpinToColor extends Command {

    /* Variables set when calling the command */
    int target;
    boolean moveClockwise;

    /*
     * Require the `color` parameter to know what color to spin the disc
     * 
     * 1 - Red
     * 2 - Green
     * 3 - Blue
     * 4 - Yellow
     * 
     */
    public SpinToColor(int color) {
        /* Require the spinner subsystem */
        requires(Robot.colorSpinner);

        /* Set the `target` variable to the value which the command was called with */
        target = color;

        int currentColor = Robot.colorSpinner.getColor();

        if (currentColor == 0) {
            moveClockwise = true;
        } else if (Math.abs(currentColor-target) == 2) {
            moveClockwise = true;
        } else if (target > currentColor && (target != 4 && currentColor != 1)) {
            moveClockwise = true;
        } else if (target < currentColor && (target != 1 && currentColor != 4)) {
            moveClockwise = false;
        } else if (target == 4 && currentColor == 1) {
            moveClockwise = false;
        } else if (target == 1 && currentColor == 4) {
            moveClockwise = true;
        } else {
            moveClockwise = true;
        }
    }

    /*
     * Executes the command
     */
    protected void execute() {

        /*
         * Moves the spinner clockwise or counterclockwise for shortest distance if possible, and executes the right
         * command
         */
            Robot.colorSpinner.spinDisc(moveClockwise);
    }

    @Override
    protected boolean isFinished() {
        return (Robot.colorSpinner.getMatchedColor() == target);
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