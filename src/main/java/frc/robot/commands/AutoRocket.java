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

public class AutoRocket extends CommandGroup {
  /**
   * Add your docs here.
   */

  public SN_DoublePreference autoRocketD1() {
    String side = AutoPreferences.getSide();
    String pos = AutoPreferences.getPosition();
    String name = "autoRocketD1";
    System.out.println(side);
    if (side == "L") {
      switch (pos) {
      case "F":
        return new SN_DoublePreference(name + side, 20.0);
      case "B":
        return new SN_DoublePreference(name + side, 21.0);
      default:
        return new SN_DoublePreference(name + "default", 20.0);
      }
    } else {
      switch (pos) {
      case "F":
        return new SN_DoublePreference(name + side, 30.0);
      case "B":
        return new SN_DoublePreference(name + side, 31.0);
      default:
        return new SN_DoublePreference(name + "default", 30.0);
      }
    }
  }

  public SN_DoublePreference autoRocketR1() {
    String side = AutoPreferences.getSide();
    String name = "autoRocketR1";

    switch (side) {
    case "L":
      return new SN_DoublePreference(name + side, 0.0);
    case "R":
      return new SN_DoublePreference(name + side, 0.0);
    default:
      return new SN_DoublePreference(name + "default", 0.0);
    }

  }

  public SN_DoublePreference autoRocketD2() {
    String side = AutoPreferences.getSide();
    String name = "autoRocketD2";

    return new SN_DoublePreference(name + side, 0.0);
  }

  public SN_DoublePreference autoRocketR2() {
    String side = AutoPreferences.getSide();
    String pos = AutoPreferences.getPosition();
    String name = "autoRocketR2";
    if (side == "L") {
      switch (pos) {
      case "F":
        return new SN_DoublePreference(name + side, -45.0);
      case "B":
        return new SN_DoublePreference(name + side, -135.0);
      default:
        return new SN_DoublePreference(name + "default", 0.0);
      }
    } else {
      switch (pos) {
      case "F":
        return new SN_DoublePreference(name + side, 45.0);
      case "B":
        return new SN_DoublePreference(name + side, 135.0);
      default:
        return new SN_DoublePreference(name + "default", 0.0);
      }
    }
  }

  public AutoRocket() {
    addSequential(new DriveRotateDistance(autoRocketD1(), autoRocketR1(), "RocketDriveRotateDistance1"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new DriveRotateDistance(autoRocketD2(), autoRocketR2(), "RocketDriveRotateDistance2"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new VisionDriveDistanceRotate(RobotPreferences.AUTO_DISTANCE_ON_TARGET,
        RobotPreferences.AUTO_ROTATE_ON_TARGET, "RocketVisionTarget"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new IntakePlaceHatchGroup());
  }
}
