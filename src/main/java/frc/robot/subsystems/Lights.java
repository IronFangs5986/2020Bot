package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.PoliceLights;

/*
 * This is the lights subsystem where anything related to lights is found
 * 
 * Author: Francisco Fabregat
 */
public class Lights extends Subsystem {
    
    /* Call lights defined in RobotMap */
    Solenoid leftRedLights = RobotMap.leftRedLights;
    Solenoid leftBlueLights = RobotMap.leftBlueLights;
    Solenoid rightRedLights = RobotMap.rightRedLights;
    Solenoid rightBlueLights = RobotMap.rightBlueLights;
    
    /* Make this class public */
    public Lights() {}

    /* Adds default command (NOT required with 2020 API)*/
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new PoliceLights());
    }

    /* Sets lights to blue */
    public void setBlue() {
        /* Set red lights off*/
        leftRedLights.set(false);
        rightRedLights.set(false);

        /* Set blue lights on */
        leftBlueLights.set(true);
        rightBlueLights.set(true);
    }

    /* Sets lights to red */
    public void setRed() {
        /* Set blue lights off */
        leftBlueLights.set(false);
        rightBlueLights.set(false);
        
        /* Set red lights on */
        leftRedLights.set(true);
        rightRedLights.set(true);
    }

    /* Sets lights to purple */
    public void setPurple() {
        /* Set blue lights on */
        leftBlueLights.set(true);
        rightBlueLights.set(true);
        
        /* Set red lights on */
        leftRedLights.set(true);
        rightRedLights.set(true);
    }

    /* Sets lights off */
    public void setOff() {
        /* Set blue lights off */
        leftBlueLights.set(false);
        rightBlueLights.set(false);
        
        /* Set red lights off */
        leftRedLights.set(false);
        rightRedLights.set(false);
    }

    /* Set lights to be alternate colors */
    public void setAlternate(boolean rb) {
        /* Set blue lights alternate */
        leftBlueLights.set(!rb);
        rightBlueLights.set(rb);
        
        /* Set red lights alternate */
        leftRedLights.set(rb);
        rightRedLights.set(!rb);
    }


}