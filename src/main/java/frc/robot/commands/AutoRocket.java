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
import frc.robot.commands.Vision.VisionDriveDistanceRotate;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class AutoRocket extends CommandGroup {
  /**
   * Add your docs here.
   */

  public SN_DoublePreference autoRocketD1() {
    String side = AutoPreferences.getSide();
    String name = "autoRocketD1";

    switch (side) {
    case "L":
      return new SN_DoublePreference(name + side, 0.0);
    case "R":
      return new SN_DoublePreference(name + side, 0.0);
    default:
      return new SN_DoublePreference(name + side, 0.0);
    }
  }

  public SN_DoublePreference autoRocketR1() {
    String side = AutoPreferences.getSide();
    String name = "autoRocketR1";

    return new SN_DoublePreference(name + side, 0.0);
  }

  public SN_DoublePreference autoRocketD2() {
    String side = AutoPreferences.getSide();
    String name = "autoRocketD2";

    return new SN_DoublePreference(name + side, 0.0);
  }

  public SN_DoublePreference autoRocketR2() {
    String side = AutoPreferences.getSide();
    String name = "autoRocketR2";

    switch (side) {
    case "L":
      return new SN_DoublePreference(name + side, 0.0);
    case "R":
      return new SN_DoublePreference(name + side, 0.0);
    default:
      return new SN_DoublePreference(name + side, 0.0);
    }
  }

  public AutoRocket() {
    addSequential(new DriveRotateDistance(autoRocketD1(), autoRocketR1(), "RocketDriveRotateDistance1"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new DriveRotateDistance(autoRocketD2(), autoRocketR2(), "RocketDriveRotateDistance2"));
    addSequential(new DoDelay(RobotPreferences.AUTO_DELAY));
    addSequential(new VisionDriveDistanceRotate(RobotPreferences.AUTO_DISTANCE_ON_TARGET,
        RobotPreferences.AUTO_ROTATE_ON_TARGET, "RocketVisionTarget"));
  }
}
