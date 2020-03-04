package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that shoots the balls manually
 */
public class ManualShoot extends Command {

    /* Variables set when calling the command */
    boolean lp;
    public ManualShoot(boolean launchpad) {
        /* Require the necessary subsystems */
        requires(Robot.shooter);
        requires(Robot.shootControl);
        requires(Robot.ballTransport);
        requires(Robot.indexer);
        requires(Robot.intake);

        lp = launchpad;
    }

    /*
     * Executes the command
     */
    protected void execute() {

        if (OI.driver.getRawButton(1) || OI.semiAutoShootButton.get()) {
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
        Robot.shooter.shoot(Robot.dashboard.getRevSpeed());
       
    }

    @Override
    protected boolean isFinished() {
        if (lp) {
            return !OI.semiAutoRevButton.get();
        } else {
            return !OI.driver.getRawButton(11);
        }
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        //Config.revSpeed = Config.defaultRevSpeed;
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