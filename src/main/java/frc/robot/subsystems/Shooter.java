package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Config;
import frc.robot.Robot;
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
        Robot.lights.setPurple();
    }

    /* Shoots at a certain rpm */
    public void shootRPM(double rpm) {
        //if (Math.abs(RobotMap.shooterEncoder.getVelocity() - rpm) <= 50) {
            RobotMap.shooterPIDController.setReference(rpm, ControlType.kVelocity);
        /*} else {
            if (RobotMap.shooterEncoder.getVelocity() < rpm) {
                shooterMotor.set(1.0);
            } else {
                shooterMotor.set(0.0);
            }
        }*/
    }

    /* Stops the shooter */
    public void stopShooter() {
        shooterMotor.stopMotor();
        Robot.lights.setOff();
    }

    public void stopRPMShooter() {
        RobotMap.shooterPIDController.setReference(0, ControlType.kVelocity);
    }

    public double calculateRPM(double inches) {
        return Config.shootRPMA*inches*inches+Config.shootRPMB*inches+Config.shootRPMC;
    }
}