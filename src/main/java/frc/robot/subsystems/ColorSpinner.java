package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Config;
import frc.robot.RobotMap;

/*
 * This is the ColorSpinner subsystem where anything related to drive is found
 */
public class ColorSpinner extends Subsystem {
    
    /* Call motors defined in RobotMap */
    CANSparkMax spinnerMotor = RobotMap.spinnerMotor;
    CANSparkMax raiseSpinnerMotor = RobotMap.raiseSpinnerMotor;

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
        double red = colorSensor.getRed();
        double green = colorSensor.getGreen();
        double blue = colorSensor.getBlue();
        
        if (red > blue && red > green) {
            return 1;
        }  else if (green > red && red > blue) {
          return 4;
        } else if (green > red && green >= blue) {
            if (Math.abs(green - blue) < 200) {
                return 3;
            } else {
                return 2;
            }
        } else if (blue >= green && blue > red) {
            return 3;
        } else {
            return 0;
        }
    }

    /* Gets the color that is currently scored based on the current sensor's reading*/
    public int getMatchedColor() {
          double red = colorSensor.getRed();
        double green = colorSensor.getGreen();
        double blue = colorSensor.getBlue();
        
        if (red > blue && red > green) {
            return 3;
        }  else if (green > red && red > blue) {
          return 2;
        } else if (green > red && green >= blue) {
            if (Math.abs(green - blue) < 200) {
                return 1;
            } else {
                return 4;
            }
        } else if (blue >= green && blue > red) {
            return 1;
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

  public void spinColor(boolean clockwise) {
    if (clockwise) {
      /* Spins the motor so the disk spins clockwise */
      spinnerMotor.set(Config.colorColorSpeed * -1);
    } else {
      /* Spins the motor so the disk spins counterclockwise */
      spinnerMotor.set(Config.colorColorSpeed);
    }
  }

    /* Moves spinnerMotor up */
    public void moveUp() {
      raiseSpinnerMotor.set(Config.colorRaiseSpeed);
    }

    /* Moves spinnerMotor down */
    public void moveDown() {
      raiseSpinnerMotor.set(Config.colorRaiseSpeed * -1);
    }

    /* Stops raiseSpinnerMotor */
    public void stopRaise() {
      raiseSpinnerMotor.set(0);
    }

    /* Stops the disk spin */
    public void stopDisc() {
        spinnerMotor.set(0);
    }
}
