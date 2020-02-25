package frc.robot.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

/*
 * This is the command that turns on tracking light
 */
public class TrackingLight extends Command {

    //Solenoid trackingLight = RobotMap.trackingLight;

    /* Variables set when calling the command */
    boolean turnOn;

    public TrackingLight(boolean on) {
        turnOn = on;
    }

    /*
     * Executes the command
     */
    protected void execute() {
       //trackingLight.set(turnOn);
       System.out.println("Light "+turnOn);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

     /*
     * Sets the subsystems to stop once the command is finished
     */
    protected void end() {}

    /*
     * Ends the command if stopped or interrupted
     */
    protected void interrupted() {
        end();
    }
}