package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that reverses the balls
 */
public class ReverseBalls extends Command {

    /* Variables set when calling the command */

    public ReverseBalls() {
        /* Require the necessary subsystems */
        requires(Robot.indexer);
        requires(Robot.ballTransport);
    }

    /*
     * Executes the command
     */
    protected void execute() {

        Robot.indexer.moveOut();
        Robot.ballTransport.moveOut();
       
    }

    @Override
    protected boolean isFinished() {
        return !OI.driver.getRawButton(4);
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.indexer.stop();
        Robot.ballTransport.stop();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}