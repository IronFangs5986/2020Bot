package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that spins the robot to adjust
 */
public class RobotAdjustSpin extends Command {

    /* Variables set when calling the command */
    boolean spinRight;

    public RobotAdjustSpin(boolean right) {
        /* Require the necessary subsystems */
        requires(Robot.driveTrain);

        spinRight = right;
    }

    /*
     * Executes the command
     */
    protected void execute() {
        if (spinRight) {
            Robot.driveTrain.spinAdjustLeft();
        } else {
            Robot.driveTrain.spinAdjustRight();
        }
    }

    @Override
    protected boolean isFinished() {
        if (spinRight) {
            return !OI.robotSpinRightButton.get();
        } else {
            return !OI.robotSpinLeftButton.get();
        }
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.driveTrain.stopTank();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}