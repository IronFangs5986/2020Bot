package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;

/*
 * This is the ColorSpinner subsystem where anything related to drive is found
 */
public class ColorSpinner extends Subsystem {
    
    /* Call spinnerMotor defined in RobotMap */
    WPI_VictorSPX spinnerMotor = RobotMap.spinnerMotor;

    ColorSensorV3 colorSensor = RobotMap.colorSensor;
    ColorMatch colorMatch = RobotMap.colorMatch;
    

    public ColorSpinner() {}

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

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

    public int getMatchedColor() {
        Color detectedColor = colorSensor.getColor();
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

    public void spinDisc(boolean clockwise) {
        if (clockwise) {
            spinnerMotor.set(-0.7);
        } else {
            spinnerMotor.set(-0.7);
        }
    }

    public void stopDisc() {
        spinnerMotor.set(0);
    }
}