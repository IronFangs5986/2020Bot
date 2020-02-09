package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/*
 * This is the Intake subsystem where anything related to ball intake is found
 */
public class Intake extends Subsystem {
    
    double intakeSpeed = 0.5;

    /* Call intakeMotor defined in RobotMap */
    CANSparkMax intakeMotor = RobotMap.intakeMotor;
    
    /* Make this class public */
    public Intake() {}

    /* Adds default command (NOT required with 2020 API)*/
    @Override
    protected void initDefaultCommand() {
    }

    /* Intakes the ball */
    public void intake() {
        intakeMotor.set(intakeSpeed);
    }

    /* Spits the ball out of the robot */
    public void moveOut() {
        intakeMotor.set(intakeSpeed * -1);
    }

    /* Stops the intake */
    public void stop() {
        intakeMotor.set(0);
    }

}