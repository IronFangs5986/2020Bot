package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
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
     * 5 - Auto
     * 
     */
    public SpinToColor(int color) {
        /* Require the spinner subsystem */
        requires(Robot.colorSpinner);

        if (color == 5) {
            String gameData = DriverStation.getInstance().getGameSpecificMessage();
        
            if(gameData.length() > 0) {
                if (gameData.charAt(0) == 'B') {
                    target = 3;
                } else if (gameData.charAt(0) == 'G') {
                    target = 2;
                } else if (gameData.charAt(0) == 'Y') {
                    target = 4;
                } else if (gameData.charAt(0) == 'R') {
                    target = 1;
                } else {
                    target = 0;
                }
            } else {
                target = 0;
            }
        } else {
            target = color;
        }

        /* Gets the current color from the color sensor */
        int currentColor = Robot.colorSpinner.getColor();

        /* Checks what is the most efficient direction to spin the disk based on the current color and target */
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
        if (target != 0) {
            Robot.colorSpinner.spinDisk(moveClockwise);
        }
    }

    @Override
    protected boolean isFinished() {
        int matched = Robot.colorSpinner.getMatchedColor();
        System.out.println(matched+":"+target);
        return (matched == target || target == 0);
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