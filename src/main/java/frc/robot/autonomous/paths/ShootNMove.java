package frc.robot.autonomous.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.actions.AutoShoot;
import frc.robot.autonomous.actions.StraightDrive;
import frc.robot.autonomous.actions.TurnInPlace;

public class ShootNMove extends CommandGroup {
    public ShootNMove() {

        /* Shoots balls 89.25 inches from target */
        addSequential(new AutoShoot(89.25));

        /* Drives back for 6ft 0in */
        addSequential(new StraightDrive(false, 6, 0, false));

        /* Turns 90 degrees */
        addSequential(new TurnInPlace(true, 90.0));

    }
}