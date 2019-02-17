/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoDelay;
import frc.robot.commands.Cascade.CascadePositionGroup;
import frc.robot.commands.Drive.DriveDistance;
import frc.robot.commands.Drive.DriveToHatch;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class IntakeFeederGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  private SN_DoublePreference AutoIntakeBackup = new SN_DoublePreference("AutoIntakeBackup", -5.0);

  public IntakeFeederGroup() {
    addSequential(new IntakeHookDeploy());
    addSequential(new DriveToHatch());
    addSequential(new DoDelay(new SN_DoublePreference("AutoIntakeTimeout", 0.5)));
    addSequential(new CascadePositionGroup(1));
    addSequential(new DriveDistance(AutoIntakeBackup, "AutoIntakeBackup"));
  }
}
