package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that climbs
 */
public class Climb extends Command {

    /* Variables set when calling the command */

    public Climb() {
        /* Require the necessary subsystems */
        requires(Robot.climber);
    }

    /*
     * Executes the command
     */
    protected void execute() {

        Robot.climber.climb();
       
    }

    @Override
    protected boolean isFinished() {
        return !OI.climbButton.get();
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.climber.stopClimb();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}