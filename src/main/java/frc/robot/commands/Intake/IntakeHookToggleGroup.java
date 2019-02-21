/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.LightsAutoCommandFinish;
import frc.robot.commands.Cascade.CascadePositionGroup;
import frc.robot.subsystems.Intake.fieldHeights;

public class IntakeHookToggleGroup extends CommandGroup {
  public IntakeHookToggleGroup() {
    addSequential(new IntakeHookToggle());
    addSequential(new IntakeWaitForHatchGroup());
    addSequential(new CascadePositionGroup(fieldHeights.LOW));
    addSequential(new LightsAutoCommandFinish());
  }
}
