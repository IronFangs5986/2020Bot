package frc.robot.autonomous.actions;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command moves the robot straight a certain distance
 */
public class StraightDrive extends Command {

    /* Initialize variables */
    boolean forwardMovement;
    double driveDistance;
    double startDistanceL;
    double startDistanceR;
    double endDistanceL;
    double endDistanceR;
    boolean setIntake;
    int counter = 0;

    /*
     * Declares public function that takes direction and distance in feet and inches
     */
    public StraightDrive(boolean forward, double userFeet, double userInches, boolean intake) {

        /* Require the necessary subsystems */
        requires(Robot.driveTrain);
        requires(Robot.intake);
        requires(Robot.indexer);
        requires(Robot.ballTransport);

        /* Sets wether the movement is forward or not */
        forwardMovement = forward;

        /* Determine distance to move in feet */
        driveDistance = (userFeet + (userInches / 12));

        setIntake = intake;
    }

    /*
     * Function runs only once when the command starts
     */
    protected void initialize() {

        /*
         * Reset encoder values
         */
        Robot.driveTrain.resetDriveEncoders();

        if (forwardMovement) {
            /*
             * Set end distance for both sides by adding the distance to move to the current
             * distance
             */
            endDistanceL = Robot.driveTrain.getLeftDistance() - driveDistance;
            endDistanceR = Robot.driveTrain.getRightDistance() + driveDistance;
        } else {
            /*
             * Set end distance for both sides by subtracting the distance to move to the
             * current distance
             */
            endDistanceL = Robot.driveTrain.getLeftDistance() + driveDistance;
            endDistanceR = Robot.driveTrain.getRightDistance() - driveDistance;
        }

        /* Determine the starting (current) distance for both sides */
        startDistanceR = Robot.driveTrain.getRightDistance();
        startDistanceL = Robot.driveTrain.getLeftDistance();

        /* Print debug information in console */
        System.out.println("Drive Distance: " + driveDistance);
        System.out.println("Starting Distance: " + Robot.driveTrain.getRightDistance());
        System.out.println("Left End Distance: " + endDistanceL);
        System.out.println("Right End Distance: " + endDistanceR);
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    protected void execute() {

        if (setIntake) {
            Robot.intake.intake();
            if (!hasFourBalls() && (RobotMap.fifthBallUltra.getRangeInches() < Config.intakeSensorDist || (counter < Config.intakeCounter && counter != 0))) {
                Robot.indexer.moveIn();
                Robot.ballTransport.moveIn();
                counter = counter + 1;
            } else {
                Robot.indexer.stop();
                Robot.ballTransport.stop();
                counter = 0;
            }
    
        } else {
            Robot.intake.stop();
            Robot.indexer.stop();
            Robot.ballTransport.stop();
        }

        /* Get distance moved since command started */
        double currentL = 0.0;
        double currentR = 0.0;

        if (forwardMovement) {
            currentL = startDistanceL - Robot.driveTrain.getLeftDistance();
            currentR = Robot.driveTrain.getRightDistance() - startDistanceR;
        } else {
            currentL = Robot.driveTrain.getLeftDistance() - startDistanceL;
            currentR = startDistanceR - Robot.driveTrain.getRightDistance();
        }
        /* Find an average of both distances moved to calculate an appropriate speed */
        //double averageDistance = (currentL + currentR) / 2;

        /* Set a tank drive movement with speed returning from getSpeed() */
        if (forwardMovement) {
            //Robot.driveTrain.tankDrive(getSpeed(currentL, driveDistance) * -1, getSpeed(currentL, driveDistance) * -1);
            Robot.driveTrain.tankDrive(-0.2, -0.2);
        } else {
            //Robot.driveTrain.tankDrive(getSpeed(currentL, driveDistance), getSpeed(currentL, driveDistance));
            Robot.driveTrain.tankDrive(0.2, 0.2);
        }
        /* Print debug information in console */
        System.out.println("Distance: " + Robot.driveTrain.getRightDistance());
    }

    /*
     * Determines when to end the command
     */
    @Override
    protected boolean isFinished() {
        if (forwardMovement) {
            /* Command ends if current distance is greater than the end distance */
            return (Robot.driveTrain.getRightDistance() >= endDistanceR);
        } else {
            /*
             * Command ends if current distance is less than the end distance, as it is
             * moving back
             */
            System.out.println("****"+Robot.driveTrain.getLeftDistance()+" "+endDistanceL);
            return (Robot.driveTrain.getRightDistance() <= endDistanceR);
        }
    }

    /*
     * Stops drivetrain when command ends
     */
    protected void end() {
        Robot.driveTrain.stopTank();
        Robot.intake.stop();
        Robot.indexer.stop();
        Robot.ballTransport.stop();
    }

    /* Checks if robot has four balls in system */
    private boolean hasFourBalls() {
        if (Robot.ballTransport.hasFirstBall() && Robot.ballTransport.hasSecondBall() && Robot.ballTransport.hasThirdBall() && Robot.ballTransport.hasFourthBall()) {
            return true;
        } else {
            return false;
        }
        
    }

    /*
     * Ends the command of autonomous is stopped or interrupted
     */
    protected void interrupted() {
        end();
    }

    /*
     * Calculates speed based on distance to target. Demo of this function can be
     * found here: https://www.desmos.com/calculator/mjxmn8nmug
     */
    private double getSpeed(double current, double total) {
        double speed = (-1 / ((-1 - ((total - 1) / 2)) * (-1 - ((total - 1) / 2))));
        speed = speed * (current - ((total - 1) / 2)) * (current - ((total - 1) / 2));
        speed = speed + 1;
        if (speed < Config.moveMinSpeed) {
            speed = Config.moveMinSpeed;
        }
        return speed;
    }
}