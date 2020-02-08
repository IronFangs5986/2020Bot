package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This is the command that moves the disc to a specific color
 */
public class Shoot extends Command {

    /* Variables set when calling the command */
    double shootRPM = 0;
    double counter = 0;
    double approxSpeed = 0;

    public Shoot() {
        /* Require the spinner subsystem */
        requires(Robot.shooter);
        requires(Robot.ballTransport);

        shootRPM = 2700.0;
        approxSpeed = (shootRPM / 5676) + 0.2;
    }

    /*
     * Executes the command
     */
    protected void execute() {

        /*
         * 
         */
        if (counter < 50) {
            Robot.ballTransport.waitForShooter();
            counter = counter + 1;
        } else {
            System.out.println("RPM: "+RobotMap.shooterEncoder.getVelocity());
            if (Math.abs(RobotMap.shooterEncoder.getVelocity()) < shootRPM) {
                Robot.ballTransport.stop();
                Robot.shooter.shoot(approxSpeed);
            } else {
                Robot.ballTransport.moveIn();
                Robot.shooter.shoot(approxSpeed);
            }
        }

        /*if (OI.driver.getRawButton(2)) {
            Robot.shooter.shoot();
        } else {
            Robot.shooter.stopShooter();
        }*/
    }

    @Override
    protected boolean isFinished() {
        return !OI.driver.getRawButton(2);
    }

     /*
     * Sets the spinner to stop once the command is finished
     */
    protected void end() {
        Robot.shooter.stopShooter();
        Robot.ballTransport.stop();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}