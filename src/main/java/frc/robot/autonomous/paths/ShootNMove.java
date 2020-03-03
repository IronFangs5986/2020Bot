package frc.robot.autonomous.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.autonomous.actions.AutoShoot;
import frc.robot.autonomous.actions.StraightDrive;
import frc.robot.autonomous.actions.TurnInPlace;

public class ShootNMove extends CommandGroup {
    public ShootNMove() {

        /* Shoots balls at 1580 RPM, estimated initial speed of 34% */
        addSequential(new AutoShoot(1800, 0.43));
        //addParallel(new WaitCommand(13));
        /* Drives back for 6ft 0in */
        addSequential(new StraightDrive(false, 6, 0, false));

        /* Turns 90 degrees */
        //addSequential(new TurnInPlace(true, 90.0));

    }
}