package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that only controls balls
 */
public class RawControl extends Command {

    /* Variables set when calling the command */
    boolean moveIn;

    public RawControl(boolean in) {
        /* Require the necessary subsystems */
        requires(Robot.shootControl);

        moveIn = in;
    }

    /*
     * Executes the command
     */
    protected void execute() {
        if (moveIn) {
            Robot.shootControl.moveToShooter();
        } else {
            Robot.shootControl.moveOut();
        }
       
    }

    @Override
    protected boolean isFinished() {
        if (moveIn) {
            return !OI.controlInButton.get();
        } else {
            return !OI.controlOutButton.get();
        }
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.shootControl.stop();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}