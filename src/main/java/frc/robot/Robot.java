/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.BallTransport;
import frc.robot.subsystems.ColorSpinner;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/*
 * This is the "main" class 
 */
public class Robot extends TimedRobot {

  /* Initialize autonomousCommand to store chosen command */
  Command autonomousCommand;

  /* Initialize OI and Subsystems */
  public static OI oi;
  public static Drive driveTrain;
  public static ColorSpinner colorSpinner;
  public static Shooter shooter;
  public static BallTransport ballTransport;
  public static Indexer indexer;
  public static Intake intake;

  /* Initialize the tracking camera */
  //public static Tracking trackingCam;

  /* Define default autonomous mode id */
  private int mode = 0;

  /* Initialize and define autonomous modes list */
  String[] autoList = { "Move Straight" };

  /* Initialize Dashboard */
  //Dashboard dashboard = new Dashboard();

  /*
   * This function is executed only once when the robot boots up
   */
  @Override
  public void robotInit() {
    /* Initialize RobotMap */
    RobotMap.init();

    /* Initialize Launchpad */
    Launchpad.init();

    /* Define OI and Subsystems */
    driveTrain = new Drive();
    colorSpinner = new ColorSpinner();
    shooter = new Shooter();
    ballTransport = new BallTransport();
    indexer = new Indexer();
    intake = new Intake();
    oi = new OI();

    /* Define the tracking camera and start stream 1 */
    //trackingCam = new Tracking();
    //trackingCam.startCameraStream1();

    /* Push autonomous list to Dashboard */
    //dashboard.setAutonomousList(autoList);

    /* Select default autonomous mode */
    //dashboard.setAutonomous(0);

    RobotMap.colorMatch.addColorMatch(RobotMap.BlueTarget);
    RobotMap.colorMatch.addColorMatch(RobotMap.GreenTarget);
    RobotMap.colorMatch.addColorMatch(RobotMap.RedTarget);
    RobotMap.colorMatch.addColorMatch(RobotMap.YellowTarget);
  }

  /*
   * This function is executed periodically in any mode (disabled, teleop,
   * autonomous, etc.)
   */
  @Override
  public void robotPeriodic() {
    /* Send battery voltage to Dashboard */
    //dashboard.setBattery(RobotController.getBatteryVoltage());

    /* Send compressor state to Dashboard */
    //dashboard.setCompressorState(RobotMap.compressor.enabled());

    /* Send remaining time to Dashboard */
    //dashboard.setTime(DriverStation.getInstance().getMatchTime());
  }

  /*
   * This function is executed only once when the robot changes into disabled mode
   */
  @Override
  public void disabledInit() {
    /* Send disabled robot mode to Dashboard*/
    //dashboard.setRobotMode("Disabled");
  }

  /*
   * This function is executed periodically when in disabled mode
   */
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }
  
  /*
   * This function is executed only once when the robot changes into autonomous
   * mode
   */
  @Override
  public void autonomousInit() {
    /* Send autonomous robot mode to Dashboard*/
    //dashboard.setRobotMode("Autonomous");

    /* Set mode variable to the chosen autonomous mode id */
    //mode = dashboard.getSelectedAutonomous();

    /* Set autonomousCommand to the right command according to the mode variable */
    if (mode == 0) {
    } else if (mode == 1) {
    } else {
    }

    /* Start the autonomous command if it has not been started already */
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }

  /*
   * This function is executed periodically when in autonomous mode
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /*
   * This function is executed only once when the robot changes into teleop mode
   */
  @Override
  public void teleopInit() {

    /* Send teleop robot mode to Dashboard*/
    //dashboard.setRobotMode("Teleop");

    /* Stop the autonomous if it is still running */
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  /*
   * This function is executed periodically when in teleop mode
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /*
   * This function is executed periodically when in test mode
   */
  @Override
  public void testPeriodic() {

  }
}
