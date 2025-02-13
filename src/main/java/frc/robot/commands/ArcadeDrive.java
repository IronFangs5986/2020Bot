package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This command runs indefinetely, and controls the Drive subsystem from user input (joystick)
 */
public class ArcadeDrive extends Command {

    double currentTurnSpeed = 0;

    /*
     * State the required driveTrain subsystem
     */
    public ArcadeDrive() {
        requires(Robot.driveTrain);
    }

    /*
     * This function is what is done when the command is run
     */
    protected void execute() {

        /* Define joystick axis */
        double moveAxis = OI.driver.getRawAxis(1);
        double turnAxis = OI.driver.getRawAxis(0);

        boolean fastMode = true;

        double turnSpeed = turnAxis;
        double moveSpeed = moveAxis;

        /* Limits turn acceleration */
        if (Math.abs(turnAxis) >= Config.turnMinSpeed) {
        if (currentTurnSpeed < turnAxis) {
            turnSpeed = currentTurnSpeed+Config.turnAccel;
        } else if (currentTurnSpeed > turnAxis) {
            turnSpeed = currentTurnSpeed-Config.turnAccel;
        }
        } else {
            turnSpeed = 0;
        }
        currentTurnSpeed = turnSpeed;

        /* Set Dead Zones */
        if (Math.abs(moveSpeed) <= Config.moveMinSpeed) {
            moveSpeed = 0;
        }
        if (Math.abs(turnSpeed) <= Config.turnMinSpeed) {
            turnSpeed = 0;
        }
        
        /* Set maximum rotation speed */
        if (Math.abs(turnSpeed) >= Config.turnMaxSpeed) {
            if (turnSpeed > 0) {
                turnSpeed = Config.turnMaxSpeed;
            } else {
                turnSpeed = Config.turnMaxSpeed * -1;
            }
        }

        /* Set maximum rotation speed */
        if (Math.abs(moveSpeed) >= Config.moveMaxSpeed) {
            if (moveSpeed > 0) {
                moveSpeed = Config.moveMaxSpeed;
            } else {
                moveSpeed = Config.moveMaxSpeed * -1;
            }
        }

        /* Sets fast or slow mode */
        if (!fastMode) {
            moveSpeed = moveSpeed * 0.8;
        }

        if (moveSpeed == 0.0 && turnSpeed == 0.0) {
            if (OI.robotSpinLeftButton.get() || OI.driver.getRawButton(3)) {
                Robot.driveTrain.spinAdjustLeft();
            } else if (OI.robotSpinRightButton.get() || OI.driver.getRawButton(2)) {
                Robot.driveTrain.spinAdjustRight();
            } else {
                /* Sets the arcadeDrive to the 2 drive axis and strafe axis */
                Robot.driveTrain.arcadeDrive(moveSpeed, turnSpeed);
            }
        } else {
            /* Sets the arcadeDrive to the 2 drive axis and strafe axis */
            Robot.driveTrain.arcadeDrive(moveSpeed, turnSpeed);
        }

    }

    /*
     * Makes command run forever
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

}