/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Cascade.CascadeBottom;
import frc.robot.commands.Cascade.CascadePositionGroup;
import frc.robot.commands.Cascade.CascadeUnweightGroup;
import frc.robot.subsystems.Intake.fieldHeights;

public class IntakeDeployGroup extends CommandGroup {
  /**
   * <ul>
   * <li>Retracts hatch ejector pistons</li>
   * <li>Unweights and locks cascade at bottom</li>
   * <li>Deploys the intake and hatch hook</li>
   * </ul>
   */
  public IntakeDeployGroup() {
    addSequential(new IntakeEjectorRetract());
    addSequential(new CascadeUnweightGroup());
    addSequential(new CascadeBottom());
    addSequential(new IntakeDeploy());
    addSequential(new IntakeHookDeploy());
    addSequential(new IntakeWaitForHatchGroup());
    addSequential(new CascadePositionGroup(fieldHeights.LOW));
  }
}
