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

public class AutoShip extends CommandGroup {
  /**
   * Add your docs here.
   */
  public SN_DoublePreference AutoShipD1() {
    String side = AutoPreferences.getSide();
    String pos = AutoPreferences.getPosition();
    String name = "autoShipD1";
    if (side == "L") {
      switch (pos) {
      case "F":
        return new SN_DoublePreference(name + side, 0.0);
      case "M":
        return new SN_DoublePreference(name + side, 0.0);
      case "B":
        return new SN_DoublePreference(name + side, 0.0);
      default:
        return new SN_DoublePreference(name + "default", 0.0);
      }
    } else {
      switch (pos) {
      case "F":
        return new SN_DoublePreference(name + side, 0.0);
      case "M":
        return new SN_DoublePreference(name + side, 0.0);
      case "B":
        return new SN_DoublePreference(name + side, 0.0);
      default:
        return new SN_DoublePreference(name + "default", 0.0);
      }
    }
  }

  public SN_DoublePreference AutoShipR1() {
    String side = AutoPreferences.getSide();
    String name = "autoShipR1";

    switch (side) {
    case "L":
      return new SN_DoublePreference(name + side, 0.0);
    case "R":
      return new SN_DoublePreference(name + side, 0.0);
    default:
      return new SN_DoublePreference(name + "default", 0.0);
    }

  }

  public SN_DoublePreference AutoShipLocationD2() {
    String side = AutoPreferences.getSide();
    String name = "autoShipD2";

    switch (side) {
    case "L":
      return new SN_DoublePreference(name + side, 0.0);
    case "R":
      return new SN_DoublePreference(name + side, 0.0);
    default:
      return new SN_DoublePreference(name + "default", 0.0);
    }
  }

  public SN_DoublePreference AutoShipR2() {
    String side = AutoPreferences.getSide();
    String pos = AutoPreferences.getPosition();
    String name = "autoShipR2";

    if (side == "L") {
      switch (pos) {
      case "F":
        return new SN_DoublePreference(name + side, 0.0);
      case "M":
        return new SN_DoublePreference(name + side, 0.0);
      case "B":
        return new SN_DoublePreference(name + side, 0.0);
      default:
        return new SN_DoublePreference(name + "default", 0.0);
      }
    } else {
      switch (pos) {
      case "F":
        return new SN_DoublePreference(name + side, 0.0);
      case "M":
        return new SN_DoublePreference(name + side, 0.0);
      case "B":
        return new SN_DoublePreference(name + side, 0.0);
      default:
        return new SN_DoublePreference(name + "default", 0.0);
      }
    }
  }

  public AutoShip() {
    addSequential(new DriveRotateDistance(AutoShipD1(), AutoShipR1(), "autoShipDriveRotateDistance1"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new DriveRotateDistance(AutoShipLocationD2(), AutoShipR2(), "autoShipDriveRoateDistance2"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new VisionDriveDistanceRotate(RobotPreferences.AUTO_DISTANCE_ON_TARGET,
        RobotPreferences.AUTO_ROTATE_ON_TARGET, "ShipVisionTarget"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new IntakePlaceHatchGroup());
  }
}
