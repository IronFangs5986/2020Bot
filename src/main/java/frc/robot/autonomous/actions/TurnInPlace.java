package frc.robot.autonomous.actions;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This command turns the robot certain degrees
 */
public class TurnInPlace extends Command {

    /* Initialize variables */
    boolean clockwiseMovement;
    double turnDegrees;
    double startDegrees;
    double endDegrees;

    /*
     * Declares public function that takes direction and degrees
     */
    public TurnInPlace(boolean clockwise, double degrees) {

        /* Require the necessary subsystems */
        requires(Robot.driveTrain);

        /* Sets whether the movement is clockwise or not */
        clockwiseMovement = clockwise;

        /* Sets the number of degrees */
        turnDegrees = degrees;

    }

    /*
     * Function runs only once when the command starts
     */
    protected void initialize() {

        /*
         * Reset gyro values
         */
        RobotMap.gyro.reset();

        if (clockwiseMovement) {
            /*
             * Set end degrees by adding the degrees to turn to the current
             * degrees
             */
            endDegrees = RobotMap.gyro.getGyroAngleY() + turnDegrees;
        } else {
             /*
             * Set end degrees by subtracting the degrees to turn to the current
             * degrees
             */
            endDegrees = RobotMap.gyro.getGyroAngleY() - turnDegrees;
        }

        /* Determine the starting (current) degrees */
        startDegrees = RobotMap.gyro.getGyroAngleY();

        /* Print debug information in console */
        System.out.println("Turn Degrees: " + turnDegrees);
        System.out.println("Starting Degrees: " + RobotMap.gyro.getGyroAngleY());
        System.out.println("End Degrees: " + endDegrees);
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    protected void execute() {

        /* Get degrees moved since command started */
        double current = RobotMap.gyro.getGyroAngleY() - startDegrees;

        /* Set a tank drive movement with speed returning from getSpeed() */
        if (clockwiseMovement) {
            Robot.driveTrain.tankDrive(getSpeed(current, turnDegrees), getSpeed(current, turnDegrees) * -1);
        } else {
            Robot.driveTrain.tankDrive(getSpeed(current, turnDegrees) * -1, getSpeed(current, turnDegrees));
        }

        /* Print debug information in console */
        System.out.println("Degrees: " + RobotMap.gyro.getGyroAngleY());
    }

    /*
     * Determines when to end the command
     */
    @Override
    protected boolean isFinished() {
        if (clockwiseMovement) {
            /* Command ends if current degrees is greater than the end degrees */
            return (RobotMap.gyro.getGyroAngleY() >= endDegrees);
        } else {
            /*
             * Command ends if current degrees is less than the end degrees
             */
            return (RobotMap.gyro.getGyroAngleY() <= endDegrees);
        }
    }

    /*
     * Stops drivetrain when command ends
     */
    protected void end() {
        Robot.driveTrain.stopTank();
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