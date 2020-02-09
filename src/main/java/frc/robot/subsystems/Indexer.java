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

    /* Moves the balls back to wait for shooter to get to correct RPMs*/
    public void moveForShooter() {
        indexMotor.set(-0.3);
    }

    /* Indexes the balls to the transport */
    public void moveIn() {
        indexMotor.set(indexSpeed);
    }

    /* Indexes the balls to the intake */
    public void moveOut() {
        indexMotor.set(indexSpeed * -1);
    }

    /* Stops the indexer */
    public void stop() {
        indexMotor.set(0);
    }

}