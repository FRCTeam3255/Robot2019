/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frc.robot.commands.DoDelay;
import frc.robot.commands.LightsAutoCommandFinish;
import frc.robot.commands.Cascade.CascadeBottomGroup;
import frc.robot.commands.Drive.DriveDistance;
import frc.robot.commands.Drive.DriveToHatch;
import frc.robot.commands.Vision.VisionRotate;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class IntakeFeederGroup extends CommandGroup {

  private SN_DoublePreference AutoIntakeBackup = new SN_DoublePreference("AutoIntakeBackup", -5.0);

  /**
   * <ul>
   * <li>Deploys the hatch finger</li>
   * <li>Drives until hatch is collected; retracts hatch finger automatically</li>
   * <li>Half-second delay</li>
   * <li>Moves cascade to position 1</li>
   * <li>Backs up the robot 5"</li>
   * </ul>
   */
  public IntakeFeederGroup() {
    requires(Robot.m_drivetrain);
    addSequential(new IntakeCargoRetract());
    // For Practice Bot
    // addSequential(new CascadePositionGroup(fieldHeights.FEEDER));
    // For Comp Bot
    addSequential(new CascadeBottomGroup());
    addSequential(new VisionRotate(RobotPreferences.VISION_ZERO_SETPOINT, "visionRotateZero"));
    addSequential(new IntakeFingerDeploy());
    addSequential(new DriveToHatch());
    // addSequential(new DoDelay(new SN_DoublePreference("AutoIntakeTimeout",
    // 0.5)));
    // addSequential(new CascadePositionGroup(fieldHeights.LOADED));
    addSequential(new DriveDistance(AutoIntakeBackup, "AutoIntakeBackup"));
    // addSequential(new CascadePositionGroup(fieldHeights.LOW));
    addSequential(new LightsAutoCommandFinish());
    addSequential(new DoDelay(new SN_DoublePreference("AutoIntakeTimeout", 5.0)));
  }
}