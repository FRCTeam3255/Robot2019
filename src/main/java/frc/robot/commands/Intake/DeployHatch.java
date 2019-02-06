/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeployHatch extends CommandGroup {

  /**
   * Add your docs here.
   */
  public DeployHatch() {
    addSequential(new IntakeDeploy());
    addSequential(new IntakeHatchDeploy());
  }
}
