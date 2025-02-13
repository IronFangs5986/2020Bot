package frc.robot.autonomous.actions;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.Robot;

/*
 * This command centers the robot with the target
 * 
 * Author: Francisco Fabregat
 */
public class CenterTarget extends Command {

    /* Initialize variables */
    boolean tapeFound;
    double degreesOff;

    /*
     * Declares public function that takes direction and distance in feet and inches
     */
    public CenterTarget() {

        /* Require the Drive subsystem */
        requires(Robot.driveTrain);

    }

    /*
     * Function runs only once when the command starts
     */
    protected void initialize() {}

    /*
     * Function running periodically as long as isFinished() returns false
     */
    protected void execute() {

        /* Get distance moved since command started */
        degreesOff = Robot.limelight.getTx();
        tapeFound = Robot.limelight.hasTarget();

        /* If the Limelight finds tape, adjust the robot accordingly until it is centered with the target */
        if (tapeFound) {
            if (Math.abs(degreesOff) > Config.shootTurnTolerance) {
                if (degreesOff > 0) {
                    Robot.driveTrain.adjustTargetRight();
                } else {
                    Robot.driveTrain.adjustTargetLeft();
                }
            } else {
                Robot.driveTrain.stopTank();
            }
        } else {
            Robot.driveTrain.stopTank();
        }
        /* Print debug information in console */
        System.out.println("Degrees off: " + degreesOff);
    }

    /*
     * Determines when to end the command
     */
    @Override
    protected boolean isFinished() {
        if (Math.abs(degreesOff) <= Config.shootTurnTolerance || !tapeFound) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Stops drivetrain when command ends
     */
    protected void end() {
        Robot.driveTrain.stopTank();
    }

    /*
     * Ends the command if autonomous is stopped or interrupted
     */
    protected void interrupted() {
        end();
    }

}