package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that does police lights
 */
public class PoliceLights extends Command {

    /* Variables set when calling the command */
    int counter = 0;

    public PoliceLights() {
        /* Require the necessary subsystems */
        requires(Robot.lights);
    }

    /*
     * Executes the command
     */
    protected void execute() {
        if (counter < 10) {
            if (counter % 2 == 0) {
                Robot.lights.setAlternate(true);
            } else {
                Robot.lights.setOff();
            }
        } else if (counter >= 10) {
            if (counter % 2 == 0) {
                Robot.lights.setAlternate(false);
            } else {
                Robot.lights.setOff();
            }
        }
        if (counter == 20) {
            counter = 0;
        } else {
            counter = counter + 1;
        }
    }

    @Override
    protected boolean isFinished() {
        return !OI.policeLightsButton.get();
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.lights.setOff();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}