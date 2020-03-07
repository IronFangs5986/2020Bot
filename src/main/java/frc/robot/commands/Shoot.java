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
    double approxSpeed = 0;
    boolean tapeFound;
    double degreesOff;
    boolean shotOnce = false;
    int waitCounter;

    public Shoot() {
        /* Require the spinner subsystem */
        requires(Robot.shooter);
        requires(Robot.ballTransport);
        requires(Robot.indexer);
        requires(Robot.shootControl);
        requires(Robot.driveTrain);
        requires(Robot.lights);
    }

    /*
     * Function runs only once when the command starts
     */
    protected void initialize() {
        Robot.limelight.ledOn();
        Robot.lights.setPurple();
    }

    /*
     * Executes the command
     */
    protected void execute() {

        /* Get degrees off, and whether there is a target found */
        degreesOff = Robot.limelight.getTx();
        tapeFound = Robot.limelight.hasTarget();

        if (tapeFound) {
            double targetDistance = Robot.limelight.getDistance();
            double shootRPM = Robot.shooter.calculateRPM(targetDistance);
            double calculatePercentage = Robot.shooter.calculatePercentage(shootRPM);

            Robot.shooter.shoot(Robot.shooter.calculateSpeed(RobotMap.shooterEncoder.getVelocity(), shootRPM, calculatePercentage));
            if (Math.abs(degreesOff) > Config.shootTurnTolerance) {
                if (degreesOff > 0) {
                    Robot.driveTrain.adjustTargetRight();
                } else {
                    Robot.driveTrain.adjustTargetLeft();
                }
            } else {
                Robot.driveTrain.stopTank();



                if (Math.abs(RobotMap.shooterEncoder.getVelocity() - shootRPM) <= Config.shootRPMTolerance || shotOnce) {
                    if (waitCounter > 30) {
                        shotOnce = true;
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
                    Robot.shootControl.stop();
                    if (!Robot.ballTransport.hasFirstBall()) {
                        Robot.ballTransport.moveIn();
                        Robot.indexer.moveIn();
                        Robot.intake.intake();
                    } else {
                        Robot.ballTransport.stop();
                        Robot.indexer.stop();
                        Robot.intake.stop();
                    }
                }




            }
        } else {
            System.out.println("No Tape found");
            Robot.driveTrain.stopTank();
        }
    }

    @Override
    protected boolean isFinished() {
            return !OI.autoShootButton.get();
    }

     /*
     * Sets the shooter to stop once the command is finished
     */
    protected void end() {
        Robot.limelight.ledOff();
        Robot.lights.setOff();
        Robot.shootControl.stop();
        Robot.shooter.stopShooter();
        Robot.ballTransport.stop();
        Robot.indexer.stop();
        Robot.driveTrain.stopTank();
        approxSpeed = 0;
        shotOnce = false;
        waitCounter = 0;
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}