/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoDelay;
import frc.robot.commands.Intake.IntakeLinkageDeploy;
import frc.robot.subsystems.Intake.fieldHeights;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class CascadeStartMatch extends CommandGroup {

  public CascadeStartMatch() {
    addSequential(new CascadePositionGroup(fieldHeights.LOADED));
    addSequential(new IntakeLinkageDeploy());
    addSequential(new DoDelay(new SN_DoublePreference("CascadeStartMatch", 0.5)));
    addSequential(new CascadePositionGroup(fieldHeights.LOW));
  }
}
