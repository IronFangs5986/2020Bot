package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that adjusts rev speed
 */
public class AdjustRevSpeed extends Command {

    /* Variables set when calling the command */
    boolean moveUp;

    public AdjustRevSpeed(Boolean up) {
        moveUp = up;
    }

    /*
     * Executes the command
     */
    protected void execute() {
        if (moveUp) {
            Config.revSpeed = Config.revSpeed + 0.5;
        } else {
            Config.revSpeed = Config.revSpeed - 0.5;
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

     /*
     * Sets the spinner to stop once the command is finished
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