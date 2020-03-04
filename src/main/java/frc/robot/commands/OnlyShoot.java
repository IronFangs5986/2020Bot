package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This is the command that only shoots balls
 */
public class OnlyShoot extends Command {

    /* Variables set when calling the command */

    public OnlyShoot() {
        /* Require the necessary subsystems */
        requires(Robot.shooter);
    }

    /*
     * Executes the command
     */
    protected void execute() {
        Robot.shooter.shoot(getSpeed(RobotMap.shooterEncoder.getVelocity(), 1800, 0.43));
        //Robot.shooter.shootRPM(1700);
    }

    @Override
    protected boolean isFinished() {
        //return false;    
        return !OI.manualShootButton.get();
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

    /*
     * Calculates speed based on distance to target. Demo of this function can be
     * found here: https://www.desmos.com/calculator/mjxmn8nmug
     */
    private double getSpeed(double current, double total, double approx) {
        double speed = (-1 / ((-1 - ((total - 1) / 2)) * (-1 - ((total - 1) / 2))));
        speed = speed * (current - ((total - 1) / 2)) * (current - ((total - 1) / 2));
        speed = speed + 1;
        if (speed < approx) {
            speed = approx;
        }
        return speed;
    }
}