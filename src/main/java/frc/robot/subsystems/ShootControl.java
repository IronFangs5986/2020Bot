package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Config;
import frc.robot.RobotMap;

/*
 * This is the Shooter Control subsystem where anything related to shooter control is found
 */
public class ShootControl extends Subsystem {

    /* Call controlMotor defined in RobotMap */
    CANSparkMax controlMotor = RobotMap.controlMotor;
    
    /* Make this class public */
    public ShootControl() {}

    /* Adds default command (NOT required with 2020 API)*/
    @Override
    protected void initDefaultCommand() {
    }

    /* Moves the balls to the shooter */
    public void moveToShooter() {
        controlMotor.set(Config.controlShootSpeed);
    }

    /* Moves the balls to the ball transport */
    public void moveOut() {
        controlMotor.set(Config.controlReverseSpeed * -1);
    }

    /* Spins the control backward to prevent balls from going to the shooter while intaking */
    public void keepBallsIn() {
        controlMotor.set(Config.controlKeepInSpeed * -1);
    }


    /* Stops the shoot control */
    public void stop() {
        controlMotor.set(0);
    }

}