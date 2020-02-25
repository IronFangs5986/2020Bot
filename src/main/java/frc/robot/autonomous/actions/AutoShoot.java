package frc.robot.autonomous.actions;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.Dashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command shoots balls in autonomous
 */
public class AutoShoot extends Command {

    /* Initialize variables */
    double inches;
    double rpm;

    /*
     * Declares public function that takes direction and distance in feet and inches
     */
    public AutoShoot(double inchesFromTarget) {

        /* Require the necessary subsystems */
        requires(Robot.indexer);
        requires(Robot.ballTransport);
        requires(Robot.shootControl);
        requires(Robot.shooter);

        inches = inchesFromTarget;

    }

    /*
     * Function runs only once when the command starts
     */
    protected void initialize() {
        rpm = Robot.shooter.calculateRPM(inches);
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    protected void execute() {

        Robot.shooter.shootRPM(rpm);

        if (Math.abs(RobotMap.shooterEncoder.getVelocity() - rpm) <= Config.shootRPMTolerance) {
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

    }

    /*
     * Determines when to end the command
     */
    @Override
    protected boolean isFinished() {
        if (!Robot.ballTransport.hasFirstBall() && !Robot.ballTransport.hasSecondBall() && !Robot.ballTransport.hasThirdBall() && !Robot.ballTransport.hasFourthBall() && !Robot.ballTransport.hasFifthBall()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Stops drivetrain when command ends
     */
    protected void end() {
        Robot.indexer.stop();
        Robot.ballTransport.stop();
        Robot.shootControl.stop();
        Robot.shooter.stopShooter();
    }

    /*
     * Ends the command of autonomous is stopped or interrupted
     */
    protected void interrupted() {
        end();
    }

}