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
    int counter = 0;

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

        if (!hasFourBalls() && (Robot.ballTransport.hasFifthBall() || (counter < Config.intakeCounter && counter != 0))) {
            Robot.indexer.moveIn();
            Robot.ballTransport.moveIn();
            counter = counter + 1;
        } else {
            Robot.indexer.stop();
            Robot.ballTransport.stop();
            counter = 0;
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

    /* Checks if robot has four balls in system */
    private boolean hasFourBalls() {
        if (Robot.ballTransport.hasFirstBall() && Robot.ballTransport.hasSecondBall() && Robot.ballTransport.hasThirdBall() && Robot.ballTransport.hasFourthBall()) {
            return true;
        } else {
            return false;
        }
        
    }
}