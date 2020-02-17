package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that shoots the balls manually
 */
public class ManualShoot extends Command {

    /* Variables set when calling the command */

    public ManualShoot() {
        /* Require the necessary subsystems */
        requires(Robot.shooter);
        requires(Robot.shootControl);
        requires(Robot.ballTransport);
        requires(Robot.indexer);
        requires(Robot.intake);
    }

    /*
     * Executes the command
     */
    protected void execute() {

        if (OI.driver.getRawButton(1)) {
            Robot.shootControl.moveToShooter();
            Robot.ballTransport.moveIn();
            Robot.indexer.moveIn();
            Robot.intake.intake();
        } else {
            Robot.shootControl.stop();
            Robot.ballTransport.stop();
            Robot.indexer.stop();
            Robot.intake.stop();
        }
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
        Robot.shootControl.stop();
        Robot.ballTransport.stop();
        Robot.indexer.stop();
        Robot.intake.stop();
        Robot.shooter.stopShooter();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}