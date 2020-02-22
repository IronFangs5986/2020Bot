package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that only transports balls
 */
public class RawTransport extends Command {

    /* Variables set when calling the command */
    boolean moveIn;

    public RawTransport(boolean in) {
        /* Require the necessary subsystems */
        requires(Robot.ballTransport);
        requires(Robot.indexer);

        moveIn = in;
    }

    /*
     * Executes the command
     */
    protected void execute() {
        if (moveIn) {
            Robot.ballTransport.moveIn();
            Robot.indexer.moveIn();
        } else {
            Robot.ballTransport.moveOut();
            Robot.indexer.moveOut();
        }
       
    }

    @Override
    protected boolean isFinished() {
        if (moveIn) {
            return !OI.transportInButton.get();
        } else {
            return !OI.transportOutButton.get();
        }
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.ballTransport.stop();
        Robot.indexer.stop();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}