package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This is the command that only intakes balls
 */
public class RawIntake extends Command {

    /* Variables set when calling the command */
    boolean moveIn;

    public RawIntake(boolean in) {
        /* Require the necessary subsystems */
        requires(Robot.intake);

        moveIn = in;
    }

    /*
     * Executes the command
     */
    protected void execute() {
        if (moveIn) {
            Robot.intake.intake();
        } else {
            Robot.intake.moveOut();
        }
       
    }

    @Override
    protected boolean isFinished() {
        if (moveIn) {
            return !OI.intakeInButton.get();
        } else {
            return !OI.intakeOutButton.get();
        }
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.intake.stop();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}