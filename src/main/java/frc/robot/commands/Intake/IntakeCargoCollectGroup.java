/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoDelay;
import frc.robot.commands.Cascade.CascadeBottomGroup;
import frc.robot.commands.Cascade.CascadePositionGroup;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class IntakeCargoCollectGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeCargoCollectGroup() {
    addSequential(new CascadeBottomGroup());
    addSequential(new IntakeDeploy());
    addSequential(new IntakeCargoCollect());
    addSequential(new DoDelay(new SN_DoublePreference("cargoCollectDelay", 0.5)));
    addSequential(new CascadePositionGroup(1));
  }
}
