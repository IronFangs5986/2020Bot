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
    int fifthCounter = 0;

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

        if (fifthCounter < 60 && (RobotMap.intakeUltra.getRangeInches() < Config.intakeSensorDist || (counter < 60 && counter != 0))) {
            Robot.indexer.moveIn();
            Robot.ballTransport.moveIn();
            counter = counter + 1;
        } else {
            Robot.indexer.stop();
            Robot.ballTransport.stop();
            counter = 0;
        }

        if (RobotMap.intakeUltra.getRangeInches() < Config.intakeSensorDist) {
            fifthCounter = fifthCounter + 1;
        } else {
            fifthCounter = 0;
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