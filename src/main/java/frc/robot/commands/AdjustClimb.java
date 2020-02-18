package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that moves the robot right or left while climbing
 */
public class AdjustClimb extends Command {

    /* Variables set when calling the command */
    boolean moveRight;

    public AdjustClimb(boolean right) {
        /* Require the necessary subsystems */
        requires(Robot.climber);

        /* Set moveRight to the provided right variable */
        moveRight = right;
    }

    /*
     * Executes the command
     */
    protected void execute() {

        if (moveRight) {
            Robot.climber.adjustRight();
        } else {
            Robot.climber.adjustLeft();
        }

    }

    @Override
    protected boolean isFinished() {
        if (moveRight) {
            return !OI.climbAdjustRightButton.get();
        } else {
            return !OI.climbAdjustLeftButton.get();
        }
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.climber.stopAdjust();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}