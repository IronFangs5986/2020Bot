package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/*
 * This is the command that performs rotation control
 */
public class RotationControl extends Command {

    /* Variables set when calling the command */

    public RotationControl() {
        /* Require the necessary subsystems */
        requires(Robot.colorSpinner);
    }

    /*
     * Executes the command
     */
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return true;
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {

    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}