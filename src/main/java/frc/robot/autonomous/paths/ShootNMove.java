package frc.robot.autonomous.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.actions.AutoShoot;
import frc.robot.autonomous.actions.StraightDrive;

/*
 * This autonomous path shoots Power Cells and moves back
 * 
 * Author: Francisco Fabregat
 */
public class ShootNMove extends CommandGroup {
    public ShootNMove() {

        /* Shoots balls at 1800 RPM, estimated initial speed of 43% */
        addSequential(new AutoShoot(1700, 0.38));

        /* Drives back for 6ft 0in */
        addSequential(new StraightDrive(false, 6, 0, false));

    }
}