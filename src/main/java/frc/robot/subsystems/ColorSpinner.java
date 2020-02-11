package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Config;
import frc.robot.RobotMap;

/*
 * This is the ColorSpinner subsystem where anything related to drive is found
 */
public class ColorSpinner extends Subsystem {
    
    /* Call spinnerMotor defined in RobotMap */
    WPI_VictorSPX spinnerMotor = RobotMap.spinnerMotor;

    /* Call colorSensor and colorMatch defined in RobotMap */
    ColorSensorV3 colorSensor = RobotMap.colorSensor;
    ColorMatch colorMatch = RobotMap.colorMatch;
    
    /* Make this class public */
    public ColorSpinner() {}

    /* Adds default command (NOT required with 2020 API)*/
    @Override
    protected void initDefaultCommand() {}

    /* Gets the color the color sensor is currently reading*/
    public int getColor() {
        Color detectedColor = colorSensor.getColor();
        ColorMatchResult match = RobotMap.colorMatch.matchClosestColor(detectedColor);
        if (match.color == RobotMap.RedTarget) {
            return 1;
          } else if (match.color == RobotMap.GreenTarget) {
            return 2;
          } else if (match.color == RobotMap.BlueTarget) {
            return 3;
          } else if (match.color == RobotMap.YellowTarget) {
            return 4;
          } else {
            return 0;
          }
    }

    /* Gets the color that is currently scored based on the current sensor's reading*/
    public int getMatchedColor() {
        Color detectedColor = RobotMap.colorSensor.getColor();
        ColorMatchResult match = RobotMap.colorMatch.matchClosestColor(detectedColor);
        if (match.color == RobotMap.RedTarget) {
            return 3;
          } else if (match.color == RobotMap.GreenTarget) {
            return 4;
          } else if (match.color == RobotMap.BlueTarget) {
            return 1;
          } else if (match.color == RobotMap.YellowTarget) {
            return 2;
          } else {
            return 0;
          }
    }

    /* Spins disk/control panel with spinnerMotor */
    public void spinDisk(boolean clockwise) {
        if (clockwise) {
          /* Spins the motor so the disk spins clockwise */
          spinnerMotor.set(Config.colorSpinSpeed * -1);
        } else {
          /* Spins the motor so the disk spins counterclockwise */
          spinnerMotor.set(Config.colorSpinSpeed);
        }
    }

    /* Spins disk/control panel with spinnerMotor */
    public void adjustDisk(boolean clockwise) {
      if (clockwise) {
        /* Spins the motor so the disk spins clockwise */
        spinnerMotor.set(Config.colorAdjustSpeed * -1);
      } else {
        /* Spins the motor so the disk spins counterclockwise */
        spinnerMotor.set(Config.colorAdjustSpeed);
      }
  }

    /* Stops the disk spin */
    public void stopDisc() {
        spinnerMotor.set(0);
    }
}