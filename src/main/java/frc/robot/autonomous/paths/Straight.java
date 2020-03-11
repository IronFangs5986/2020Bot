package frc.robot.autonomous.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.actions.StraightDrive;

/*
 * This autonomous path just drives forward
 * 
 * Author: Francisco Fabregat
 */
public class Straight extends CommandGroup {
    public Straight() {

        /* Drives forward for 6ft 0in */
        addSequential(new StraightDrive(true, 6, 0, false));
    }
}

