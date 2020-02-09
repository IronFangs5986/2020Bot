package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/*
 * This is the Climber subsystem where anything related to climber is found
 */
public class Climber extends Subsystem {
    
    double climbSpeed = 1;
    double adjustSpeed = 0.3;

    /* Call motors defined in RobotMap */
    CANSparkMax climbMotor = RobotMap.climbMotor;
    CANSparkMax adjustMotor = RobotMap.adjustMotor;

    /* Make this class public */
    public Climber() {}

    /* Adds default command (NOT required with 2020 API)*/
    @Override
    protected void initDefaultCommand() {
    }

    /* Climbs */
    public void climb() {
        climbMotor.set(climbSpeed);
    }

    /* Stops the climb motor */
    public void stopClimb() {
        climbMotor.set(0);
    }

    /* Adjusts to the left */
    public void adjustLeft() {
        adjustMotor.set(adjustSpeed * -1);
    }

    /* Adjusts to the right */
    public void adjustRight() {
        adjustMotor.set(adjustSpeed);
    }

    /* Stops the adjust motor */
    public void stopAdjust() {
        adjustMotor.set(0);
    }

}