package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/*
 * This is the Indexer subsystem where anything related to ball indexer is found
 */
public class Indexer extends Subsystem {
    
    double indexSpeed = 0.5;

    /* Call indexMotor defined in RobotMap */
    CANSparkMax indexMotor = RobotMap.indexMotor;
    
    /* Make this class public */
    public Indexer() {}

    /* Adds default command (NOT required with 2020 API)*/
    @Override
    protected void initDefaultCommand() {
    }

    public void moveIn() {
        indexMotor.set(indexSpeed);
    }

    public void moveOut() {
        indexMotor.set(indexSpeed * -1);
    }

    public void stop() {
        indexMotor.set(0);
    }

}