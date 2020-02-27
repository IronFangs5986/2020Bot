package frc.robot.autonomous.actions;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command shoots balls in autonomous
 */
public class AutoShoot extends Command {

    /* Initialize variables */
    //double inches;
    double spinRpm;
    double moveSpeed;
    int waitCounter;
    int endCounter;

    /*
     * Declares public function that takes direction and distance in feet and inches
     */
    public AutoShoot(double rpm, double speed) {

        /* Require the necessary subsystems */
        requires(Robot.indexer);
        requires(Robot.ballTransport);
        requires(Robot.shootControl);
        requires(Robot.shooter);

        spinRpm = rpm;
        moveSpeed = speed;
        //inches = inchesFromTarget;

    }

    /*
     * Function runs only once when the command starts
     */
    protected void initialize() {
        //rpm = Robot.shooter.calculateRPM(inches);
        Robot.dashboard.setRevSpeed(moveSpeed);
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    protected void execute() {

        //Robot.shooter.shootRPM(rpm);
        Robot.shooter.shoot(Robot.dashboard.getRevSpeed());

            if (Math.abs(RobotMap.shooterEncoder.getVelocity() - spinRpm) <= Config.shootRPMTolerance) {
                if (waitCounter > 30) {
                    Robot.shootControl.moveToShooter();
                    Robot.ballTransport.moveIn();
                    Robot.indexer.moveIn();
                    Robot.intake.intake();
                } else {
                    waitCounter = waitCounter + 1;
                    Robot.shootControl.stop();
                    Robot.ballTransport.stop();
                    Robot.indexer.stop();
                    Robot.intake.stop();
                }
            } else {
                waitCounter = 0;

                if (RobotMap.shooterEncoder.getVelocity() < spinRpm) {
                    Robot.dashboard.setRevSpeed(Robot.dashboard.getRevSpeed() + 0.025);
                } else {
                    Robot.dashboard.setRevSpeed(Robot.dashboard.getRevSpeed() - 0.025); 
                }
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
            if (endCounter > 30) {
                return true;
            } else {
                endCounter = endCounter + 1;
                return false;
            }
        } else {
            endCounter = 0;
            return false;
        }
    }

    /*
     * Stops drivetrain when command ends
     */
    protected void end() {
        Robot.dashboard.setRevSpeed(Config.defaultRevSpeed);
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