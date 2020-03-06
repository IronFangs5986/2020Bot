package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This command centers the robot with the target
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
    protected void initialize() {
        Robot.limelight.ledOn();
    }

    /*
     * Function running periodically as long as isFinished() returns false
     */
    protected void execute() {

        /* Get distance moved since command started */
        degreesOff = Robot.limelight.getTx();
        tapeFound = Robot.limelight.hasTarget();

        if (tapeFound) {
            System.out.println("Tape found");
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
            System.out.println("No Tape found");
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
        if (!OI.autoShootButton.get()) {
            return true;
        } else {
            return false;
        }
        /*if (Math.abs(degreesOff) <= Config.shootTurnTolerance || !tapeFound) {
            return true;
        } else {
            return false;
        }*/
    }

    /*
     * Stops drivetrain when command ends
     */
    protected void end() {
        Robot.driveTrain.stopTank();
        Robot.limelight.ledOff();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }

}