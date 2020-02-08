package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;
import frc.robot.commands.Shoot;

/*
 * This is the Shooter subsystem where anything related to shooter is found
 */
public class Shooter extends Subsystem {
    
    /* Call spinnerMotor defined in RobotMap */
    CANSparkMax shooterMotor = RobotMap.shooterMotor;
    
    /* Make this class public */
    public Shooter() {}

    /* Adds default command (NOT required with 2020 API)*/
    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new Shoot());
    }

    /* Gets the color the color sensor is currently reading*/
    public void shoot(double speed) {
        shooterMotor.set(speed * -1);
    }

    /* Stops the disk spin */
    public void stopShooter() {
        shooterMotor.set(0);
    }
}