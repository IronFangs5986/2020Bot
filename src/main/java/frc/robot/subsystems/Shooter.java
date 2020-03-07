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
    }

    /* Shoots at a certain rpm */
    public void shootRPM(double rpm) {
        RobotMap.shooterPIDController.setReference(rpm, ControlType.kVelocity);
    }

    /* Stops the shooter */
    public void stopShooter() {
        shooterMotor.stopMotor();
    }

    /* Stops shooter when run through PID */
    public void stopRPMShooter() {
        RobotMap.shooterPIDController.setReference(0, ControlType.kVelocity);
    }

    /* Calculates RPM to spin shooter based on parabola */
    public double calculateRPM(double inches) {
        return Config.shootRPMA*inches*inches+Config.shootRPMB*inches+Config.shootRPMC;
    }

    /* Calculates approximate percentage to spin motor based on RPM to shoot */
    public double calculatePercentage(double rpm) {
        return 0.35;
    }

    /*
     * Calculates speed based on distance to target. Demo of this function can be
     * found here: https://www.desmos.com/calculator/mjxmn8nmug
     */
    public double calculateSpeed(double current, double total, double min) {
        double speed = (-1 / ((-1 - ((total - 1) / 2)) * (-1 - ((total - 1) / 2))));
        speed = speed * (current - ((total - 1) / 2)) * (current - ((total - 1) / 2));
        speed = speed + 1;
        if (speed < min) {
            speed = min;
        }
        return speed;
    }
}