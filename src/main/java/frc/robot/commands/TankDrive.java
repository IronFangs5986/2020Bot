package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.OI;
import frc.robot.Robot;

/*
 * This command runs indefinetely, and controls the Drive subsystem from user input (joystick)
 */
public class TankDrive extends Command {

    double currentTurnSpeed = 0;

    /*
     * State the required driveTrain subsystem
     */
    public TankDrive() {
        requires(Robot.driveTrain);
    }

    /*
     * This function is what is done when the command is run
     */
    protected void execute() {

        /* Define joystick axis */
        double leftAxis = OI.driver.getRawAxis(1);
        double rightAxis = OI.driver.getRawAxis(5);


        double leftSpeed = leftAxis;
        double rightSpeed = rightAxis;

        /* Set Dead Zones */
        if (Math.abs(leftSpeed) <= Config.moveMinSpeed) {
            leftSpeed = 0;
        }
        if (Math.abs(rightSpeed) <= Config.moveMinSpeed) {
            rightSpeed = 0;
        }
        
        /* Set left maximum speed */
        if (Math.abs(leftSpeed) >= Config.moveMaxSpeed) {
            if (leftSpeed > 0) {
                leftSpeed = Config.moveMaxSpeed;
            } else {
                leftSpeed = Config.moveMaxSpeed * -1;
            }
        }

        /* Set right maximum speed */
        if (Math.abs(rightSpeed) >= Config.moveMaxSpeed) {
            if (rightSpeed > 0) {
                rightSpeed = Config.moveMaxSpeed;
            } else {
                rightSpeed = Config.moveMaxSpeed * -1;
            }
        }
        
        if (leftSpeed == 0.0 && rightSpeed == 0.0) {
            if (OI.robotSpinLeftButton.get() || OI.driver.getRawButton(3)) {
                Robot.driveTrain.spinAdjustLeft();
            } else if (OI.robotSpinRightButton.get() || OI.driver.getRawButton(2)) {
                Robot.driveTrain.spinAdjustRight();
            } else {
                /* Sets the arcadeDrive to the 2 drive axis and strafe axis */
                Robot.driveTrain.tankDrive(leftSpeed, rightSpeed);
            }
        } else {
            /* Sets the arcadeDrive to the 2 drive axis and strafe axis */
            Robot.driveTrain.tankDrive(leftSpeed, rightSpeed);
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