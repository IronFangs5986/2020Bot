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
            Robot.lights.setAlternate(true);
        } else if (counter >= 10 && counter <= 20) {
            Robot.lights.setAlternate(false);
            if (counter == 20) {
                counter = 0;
            }
        }
       counter = counter + 1;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}