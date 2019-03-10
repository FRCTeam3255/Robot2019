/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.AutoPreferences;
import frc.robot.RobotPreferences;
import frc.robot.commands.Drive.DriveRotateDistance;
import frc.robot.commands.Intake.IntakePlaceHatchGroup;
import frc.robot.commands.Vision.VisionDriveDistanceRotate;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class AutoShipFrontFront extends CommandGroup {
  /**
   * Add your docs here.
   */

  public SN_DoublePreference autoShipFFD1() {
    String side = AutoPreferences.getSide();
    String name = "autoShipFFD1";

    switch (side) {
    case "L":
      return new SN_DoublePreference(name + side, 40.0);
    case "R":
      return new SN_DoublePreference(name + side, 41.0);
    default:
      return new SN_DoublePreference(name + "default", 42.0);
    }
  }

  public SN_DoublePreference autoShipFFR1() {
    String side = AutoPreferences.getSide();
    String name = "autoShipFFR1";

    return new SN_DoublePreference(name + side, 0.0);
  }

  public SN_DoublePreference autoShipFFD2() {
    String side = AutoPreferences.getSide();
    String name = "autoShipFFD1";

    return new SN_DoublePreference(name + side, 0.0);
  }

  public SN_DoublePreference autoShipFFR2() {
    String side = AutoPreferences.getSide();
    String name = "autoShipFFR2";

    switch (side) {
    case "L":
      return new SN_DoublePreference(name + side, 60.0);
    case "R":
      return new SN_DoublePreference(name + side, 61.0);
    default:
      return new SN_DoublePreference(name + "default", 62.0);
    }
  }

  public AutoShipFrontFront() {
    addSequential(new DriveRotateDistance(autoShipFFD1(), autoShipFFR1(), "autoShipFFDriveRotateDistance1"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new DriveRotateDistance(autoShipFFD2(), autoShipFFR2(), "autoShipFFDriveRotateDistance2"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new VisionDriveDistanceRotate(RobotPreferences.AUTO_DISTANCE_ON_TARGET,
        RobotPreferences.AUTO_ROTATE_ON_TARGET, "ShipFrontFrontVisionTarget"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new IntakePlaceHatchGroup());
  }
}
