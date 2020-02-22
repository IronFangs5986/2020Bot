package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This is the command that performs rotation control
 */
public class RotationControl extends Command {

    /* Variables set when calling the command */
    double controlPanelCircumference = 32.0 * Math.PI;
    double motorCircumference = 1.25 * Math.PI;
    double moveDistance = (controlPanelCircumference/motorCircumference) * 4.0;

    public RotationControl() {
        /* Require the necessary subsystems */
        requires(Robot.colorSpinner);
        System.out.println("Pos " + moveDistance);
        RobotMap.spinnerEncoder.setPosition(0.0);
    }

    /*
     * Executes the command
     */
    protected void execute() {
        Robot.colorSpinner.spinDisk(true);
    }

    @Override
    protected boolean isFinished() {
        if (Math.abs(RobotMap.spinnerEncoder.getPosition()) > moveDistance) {
            return true;
        } else {
            return false;
        }
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.colorSpinner.stopDisc();
        RobotMap.spinnerEncoder.setPosition(0.0);
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}