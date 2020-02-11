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
        double turnAxis = OI.driver.getRawAxis(5);
        //double limitAxis = OI.driver.getRawAxis(3);

        //System.out.println(xAxis+" "+turnAxis);

        double turnSpeed = turnAxis/0.48;
        double moveSpeed = moveAxis/0.52;

        if (moveSpeed > 0.9) {
            moveSpeed = 1;
        } else if (moveSpeed < -0.9) {
            moveSpeed = -1;
        }

        if (turnSpeed > 0.9) {
            turnSpeed = 1;
        } else if (turnSpeed < -0.9) {
            turnSpeed = -1;
        }

        //System.out.println(moveSpeed+" "+turnSpeed);

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

        /*if (limitAxis < 0 && Math.abs(moveSpeed) > 0.8) {
            if (moveSpeed > 0) {
                moveSpeed = 0.8;
            } else {
                moveSpeed = -0.8;
            }
        }*/

        /* Sets the arcadeDrive to the 2 drive axis and strafe axis */
        Robot.driveTrain.arcadeDrive(moveSpeed, turnSpeed);
    }

    /*
     * Makes command run forever
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

}