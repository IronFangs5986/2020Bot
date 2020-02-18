package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that raises or lowers the spinner
 */
public class MoveSpinner extends Command {

    /* Variables set when calling the command */
    boolean moveUp;

    public MoveSpinner(boolean up) {
        /* Require the necessary subsystems */
        requires(Robot.colorSpinner);

        /* Set moveUp to the provided up variable */
        moveUp = up;
    }

    /*
     * Executes the command
     */
    protected void execute() {

        if (moveUp) {
            Robot.colorSpinner.moveUp();
        } else {
            Robot.colorSpinner.moveDown();
        }

    }

    @Override
    protected boolean isFinished() {
        if (moveUp) {
            return !OI.spinnerUpButton.get();
        } else {
            return !OI.spinnerDownButton.get();
        }
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.colorSpinner.stopRaise();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}