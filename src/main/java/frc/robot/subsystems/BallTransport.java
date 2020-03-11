package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Config;
import frc.robot.RobotMap;

/*
 * This is the Ball transport subsystem where anything related to ball transport is found
 * 
 * Author: Francisco Fabregat
 */
public class BallTransport extends Subsystem {
    
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
        transportMotor.set(Config.ballTransportSpeed);
    }

    /* Transports balls to the indexer */
    public void moveOut() {
        transportMotor.set(Config.ballTransportSpeed * -1 * (34.0/70.0));
    }
 
    /* Moves the balls back to wait for shooter to get to correct RPMs*/
    public void moveForShooter() {
        transportMotor.set(Config.ballTransportBackSpeed * -1 * (34.0/70.0));
    }

    /* Determines whether ball 1 is in the robot */
    public boolean hasFirstBall() {
        return (RobotMap.firstBallUltra.getRangeInches() < Config.ballSensorDist && RobotMap.firstBallUltra.getRangeInches() != 0.0);
    }

    /* Determines whether ball 2 is in the robot */
    public boolean hasSecondBall() {
        return (RobotMap.secondBallUltra.getRangeInches() < Config.ballSensorDist && RobotMap.secondBallUltra.getRangeInches() != 0.0);
    }

    /* Determines whether ball 3 is in the robot */
    public boolean hasThirdBall() {
        return (RobotMap.thirdBallUltra.getRangeInches() < Config.ballSensorDist && RobotMap.thirdBallUltra.getRangeInches() != 0.0);
    }

    /* Determines whether ball 4 is in the robot */
    public boolean hasFourthBall() {
        return (RobotMap.fourthBallUltra.getRangeInches() < Config.ballSensorDist && RobotMap.fourthBallUltra.getRangeInches() != 0.0);
    }

    /* Determines whether ball 5 is in the robot */
    public boolean hasFifthBall() {
        return (RobotMap.fifthBallUltra.getRangeInches() < Config.intakeSensorDist && RobotMap.fifthBallUltra.getRangeInches() != 0.0);
    }

    /* Stops the transport */
    public void stop() {
        transportMotor.set(0);
    }

}