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
    double motorCircumference = 1.0 * Math.PI;
    double distanceToSpin = controlPanelCircumference * 4.0;

    public RotationControl() {
        /* Require the necessary subsystems */
        requires(Robot.colorSpinner);

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
        return true;
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.colorSpinner.stopDisc();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}