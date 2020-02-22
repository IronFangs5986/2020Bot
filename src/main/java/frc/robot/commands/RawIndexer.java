package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that only indexes balls
 */
public class RawIndexer extends Command {

    /* Variables set when calling the command */
    boolean moveIn;

    public RawIndexer(boolean in) {
        /* Require the necessary subsystems */
        requires(Robot.indexer);

        moveIn = in;
    }

    /*
     * Executes the command
     */
    protected void execute() {
        if (moveIn) {
            Robot.indexer.moveIn();
        } else {
            Robot.indexer.moveOut();
        }
       
    }

    @Override
    protected boolean isFinished() {
        if (moveIn) {
            return !OI.indexerInButton.get();
        } else {
            return !OI.indexerOutButton.get();
        }
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.indexer.stop();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}