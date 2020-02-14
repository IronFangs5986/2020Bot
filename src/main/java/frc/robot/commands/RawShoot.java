package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that shoots the balls
 */
public class RawShoot extends Command {

    /* Variables set when calling the command */

    public RawShoot() {
        /* Require the necessary subsystems */
        requires(Robot.shooter);
    }

    /*
     * Executes the command
     */
    protected void execute() {

        Robot.shooter.shoot(0.8);
       
    }

    @Override
    protected boolean isFinished() {
        return !OI.driver.getRawButton(11);
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.shooter.stopShooter();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}