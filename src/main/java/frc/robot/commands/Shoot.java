package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This is the command that soots the balls
 */
public class Shoot extends Command {

    /* Variables set when calling the command */
    double shootRPM = 0;
    double counter = 0;
    double approxSpeed = 0;
    boolean lp;

    public Shoot(boolean launchpad) {
        /* Require the spinner subsystem */
        requires(Robot.shooter);
        requires(Robot.ballTransport);
        requires(Robot.indexer);
        requires(Robot.shootControl);
        requires(Robot.driveTrain);

        lp = launchpad;

        counter = 0;

        shootRPM = 2700.0;

        approxSpeed = (shootRPM / Config.maxShootRPM) + 0.2;
    }

    /*
     * Executes the command
     */
    protected void execute() {

        /*
         * 
         */
        /*if (counter < 50) {
            Robot.intake.stop();
            Robot.ballTransport.moveForShooter();
            Robot.indexer.moveForShooter();
            counter = counter + 1;
        } else {
            System.out.println("RPM: "+RobotMap.shooterEncoder.getVelocity());
            if (Math.abs(RobotMap.shooterEncoder.getVelocity()) < shootRPM) {
                Robot.ballTransport.stop();
                Robot.indexer.stop();
                Robot.shooter.shoot(approxSpeed);
            } else {
                Robot.ballTransport.moveIn();
                Robot.indexer.moveIn();
                Robot.shooter.shoot(approxSpeed);
            }
        }*/

        double targetX = 0.0;
        if (Math.abs(targetX) <= Config.shootTurnTolerance) {
            Robot.driveTrain.stopTank();
            System.out.println("RPM: "+RobotMap.shooterEncoder.getVelocity());
            if (Math.abs(RobotMap.shooterEncoder.getVelocity()) < shootRPM) {
                Robot.shootControl.stop();
                Robot.ballTransport.stop();
                Robot.indexer.stop();
                Robot.shooter.shoot(approxSpeed);
            } else {
                Robot.shootControl.moveToShooter();
                Robot.ballTransport.moveIn();
                Robot.indexer.moveIn();
                Robot.shooter.shoot(approxSpeed);
            }
        } else if (targetX < 0.0) {
            Robot.shootControl.stop();
            Robot.shooter.stopShooter();
            Robot.ballTransport.stop();
            Robot.indexer.stop();
            Robot.driveTrain.adjustTargetLeft();
        } else if (targetX > 0.0) {
            Robot.shootControl.stop();
            Robot.shooter.stopShooter();
            Robot.ballTransport.stop();
            Robot.indexer.stop();
            Robot.driveTrain.adjustTargetRight();
        }

        /*if (OI.driver.getRawButton(2)) {
            Robot.shooter.shoot();
        } else {
            Robot.shooter.stopShooter();
        }*/
    }

    @Override
    protected boolean isFinished() {
        if (lp) {
            return !OI.autoShootButton.get();
        } else {
            return !OI.driver.getRawButton(2);
        }
    }

     /*
     * Sets the spinner to stop once the command is finished
     */
    protected void end() {
        Robot.shootControl.stop();
        Robot.shooter.stopShooter();
        Robot.ballTransport.stop();
        Robot.indexer.stop();
        Robot.driveTrain.stopTank();
        shootRPM = 0;
        counter = 0;
        approxSpeed = 0;
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}