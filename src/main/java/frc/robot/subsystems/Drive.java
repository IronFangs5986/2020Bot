package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Config;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

/*
 * This is the Drive subsystem where anything related to drive is found
 */
public class Drive extends Subsystem {

    /* Call DifferentialDrive defined in RobotMap */
    DifferentialDrive robotDrive = RobotMap.robotDrive;

    public Drive() {}

    /*
     * Sets ArcadeDrive as default command to execute
     */
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }

    /*
     * Arcade drive is used for teleop (manual driving)
     */
    public void arcadeDrive(double moveAxis, double rotateAxis) {

        /* Sets arcadeDrive values */
        robotDrive.arcadeDrive(-moveAxis, -rotateAxis);
    }

    /*
     * Tank drive is used for autonomous
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        /* Sets tankDrive values */
        robotDrive.tankDrive(leftSpeed, rightSpeed);
    }

    /*
     * Adjusts robot to turn to the left for target
     */
    public void adjustTargetLeft() {
        /* Sets tankDrive values */
        robotDrive.tankDrive(Config.driveTargetAdjustSpeed * -1, Config.driveTargetAdjustSpeed);
    }

    /*
     * Adjusts robot to turn to the right for target
     */
    public void adjustTargetRight() {
        /* Sets tankDrive values */
        robotDrive.tankDrive(Config.driveTargetAdjustSpeed, Config.driveTargetAdjustSpeed * -1);
    }

    /*
     * Return distance recorded by left drivetrain encoder
     */
    public double getLeftDistance() {
        return 0.0;
    }

    /*
     * Return distance recorded by right drivetrain encoder
     */
    public double getRightDistance() {
        return 0.0;
    }

    /*
     * Stops tank drivetrain by setting speeds to 0
     */
    public void stopTank() {
        robotDrive.arcadeDrive(0.0, 0.0);
        robotDrive.tankDrive(0.0, 0.0);
    }

}