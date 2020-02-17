package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that intakes the balls
 */
public class IntakeBalls extends Command {

    /* Variables set when calling the command */

    public IntakeBalls() {
        /* Require the necessary subsystems */
        requires(Robot.intake);
        requires(Robot.indexer);
        requires(Robot.ballTransport);
        requires(Robot.shootControl);
    }

    /*
     * Executes the command
     */
    protected void execute() {

        Robot.intake.intake();
        Robot.indexer.moveIn();
        Robot.ballTransport.moveIn();
        Robot.shootControl.keepBallsIn();

    }

    @Override
    protected boolean isFinished() {
        return !OI.driver.getRawButton(3);
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.intake.stop();
        Robot.indexer.stop();
        Robot.ballTransport.stop();
        Robot.shootControl.stop();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}