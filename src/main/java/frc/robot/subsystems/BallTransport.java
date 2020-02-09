package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/*
 * This is the Ball transport subsystem where anything related to ball transport is found
 */
public class BallTransport extends Subsystem {
    
    double transportSpeed = 0.5;

    /* Call transportMotor defined in RobotMap */
    CANSparkMax transportMotor = RobotMap.transportMotor;
    
    /* Make this class public */
    public BallTransport() {}

    /* Adds default command (NOT required with 2020 API)*/
    @Override
    protected void initDefaultCommand() {
    }

    /* Transports the balls to the shooter */
    public void moveIn() {
        transportMotor.set(transportSpeed);
    }

    /* Transports balls to the indexer */
    public void moveOut() {
        transportMotor.set(transportSpeed * -1);
    }
 
    /* Moves the balls back to wait for shooter to get to correct RPMs*/
    public void moveForShooter() {
        transportMotor.set(-0.3);
    }

    /* Stops the transport */
    public void stop() {
        transportMotor.set(0);
    }

}