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
        Robot.shooter.shoot(Robot.shooter.calculateSpeed(RobotMap.shooterEncoder.getVelocity(), 1900, 0.43));
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

}