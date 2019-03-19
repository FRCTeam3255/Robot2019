/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoDelay;
import frc.robot.commands.LightsAutoCommandFinish;
import frc.robot.commands.Drive.DriveDistance;
import frc.robot.commands.Drive.DriveToWall;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class IntakeCargoEjectGroup extends CommandGroup {
  /**
   * Add your docs here.
   */

  private SN_DoublePreference AutoPlaceBackup = new SN_DoublePreference("AutoPlaceBackup", -5.0);

  public IntakeCargoEjectGroup() {
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
    addSequential(new DriveToWall());
    addSequential(new IntakeCargoEject());
    addSequential(new DoDelay(new SN_DoublePreference("AutoPlaceTimeout", 2.0)));
    addSequential(new DriveDistance(AutoPlaceBackup, "AutoPlaceBackup"));
    addSequential(new LightsAutoCommandFinish());
  }
}