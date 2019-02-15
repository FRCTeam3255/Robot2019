/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Cascade.CascadeBottom;
import frc.robot.commands.Cascade.CascadeLockDogtooth;
import frc.robot.commands.Cascade.CascadeUnweight;

public class IntakeDown extends CommandGroup {

  /**
   * Add your docs here.
   */
  public IntakeDown() {
    addSequential(new IntakeHatchReload());
    addSequential(new CascadeUnweight());
    addSequential(new CascadeBottom());
    addSequential(new CascadeLockDogtooth());
    addSequential(new IntakeHorizontal());
    addSequential(new IntakeDeployHook());
  }
}
