/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotPreferences;
import frc.robot.commands.DoDelay;
import frc.robot.commands.LightsAutoCommandFinish;
import frc.robot.commands.Drive.DriveDistance;
import frc.robot.commands.Drive.DriveToWall;
import frc.robot.commands.Vision.VisionRotate;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class IntakePlaceHatchGroup extends CommandGroup {

  private SN_DoublePreference AutoPlaceBackup = new SN_DoublePreference("AutoPlaceBackup", -12.0);

  /**
   * <ul>
   * <li>Drives robot forward until it hits a wall</li>
   * <li>Deploys hatch hook and ejects a hatch</li>
   * <li>Half-second delay</li>
   * <li>Backs up robot 5"</li>
   * </ul>
   */
  public IntakePlaceHatchGroup() {
    addSequential(new VisionRotate(RobotPreferences.VISION_ZERO_SETPOINT, "visionRotateZero"));
    addSequential(new DriveToWall());
    addSequential(new IntakeHatchEject());
    addSequential(new DoDelay(new SN_DoublePreference("AutoPlaceTimeout", 0.5)));
    addSequential(new DriveDistance(AutoPlaceBackup, "AutoPlaceBackup"));
    addSequential(new LightsAutoCommandFinish());
  }
}
