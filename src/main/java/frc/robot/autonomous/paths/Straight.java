package frc.robot.autonomous.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.actions.StraightDrive;

public class Straight extends CommandGroup {
    public Straight() {

        /* Drives forward for ~19ft 0in */
         addSequential(new StraightDrive(true, 19, 0, true));
    }
}

