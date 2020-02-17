package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/*
 * This is the Shooter subsystem where anything related to shooter is found
 */
public class Shooter extends Subsystem {
    
    /* Call shooterMotor defined in RobotMap */
    CANSparkMax shooterMotor = RobotMap.shooterMotor;
    
    /* Make this class public */
    public Shooter() {}

    /* Adds default command (NOT required with 2020 API)*/
    @Override
    protected void initDefaultCommand() {}

    /* Sets the shooter to move at a given speed */
    public void shoot(double speed) {
        shooterMotor.set(speed);
    }

    /* Stops the shooter */
    public void stopShooter() {
        shooterMotor.set(0);
    }
}