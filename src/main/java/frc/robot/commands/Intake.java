package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/*
 * This is the command that intakes balls automatically
 */
public class Intake extends Command {

    /* Variables set when calling the command */

    public Intake() {
        /* Require the necessary subsystems */
        requires(Robot.intake);
        requires(Robot.indexer);
        requires(Robot.ballTransport);
    }

    /*
     * Executes the command
     */
    protected void execute() {
        Robot.intake.intake();
        if (RobotMap.intakeUltra.getValue() * 0.125 < Config.intakeSensorDist) {
            Robot.indexer.moveIn();
            Robot.ballTransport.moveIn();
        } else {
            Robot.indexer.stop();
            Robot.ballTransport.stop();
        }
    }

    @Override
    protected boolean isFinished() {
            return !OI.autoIntakeButton.get();
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {
        Robot.intake.stop();
        Robot.indexer.stop();
        Robot.ballTransport.stop();
    }

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}