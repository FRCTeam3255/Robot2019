/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.AutoPreferences;
import frc.robot.commands.Drive.DriveRotateDistance;
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
      return new SN_DoublePreference(name + side, 0.0);
    case "R":
      return new SN_DoublePreference(name + side, 0.0);
    default:
      return new SN_DoublePreference(name + side, 0.0);
    }
  }

  public SN_DoublePreference autoShipFFR1() {
    String side = AutoPreferences.getSide();
    String name = "autoShipFFR1";

    return new SN_DoublePreference(name + side, 0.0);
  }

  public SN_DoublePreference autoShipFFD2() {
    String side = AutoPreferences.getSide();
    String name = "autoShipFFR1";

    return new SN_DoublePreference(name + side, 0.0);
  }

  public SN_DoublePreference autoShipFFR2() {
    String side = AutoPreferences.getSide();
    String name = "autoShipFFR2";

    switch (side) {
    case "L":
      return new SN_DoublePreference(name + side, 0.0);
    case "R":
      return new SN_DoublePreference(name + side, 0.0);
    default:
      return new SN_DoublePreference(name + side, 0.0);
    }
  }

  // Add Commands here:
  // e.g. addSequential(new Command1());
  // addSequential(new Command2());
  // these will run in order.

  // To run multiple commands at the same time,
  // use addParallel()
  // e.g. addParallel(new Command1());
  // addSequential(new Command2());
  // Command1 and Command2 will run in parallel.

  // A command group will require all of the subsystems that each member
  // would require.
  // e.g. if Command1 requires chassis, and Command2 requires arm,
  // a CommandGroup containing them would require both the chassis and the
  // arm.
  public AutoShipFrontFront() {
  }
}
