/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotPreferences;
import frc.robot.commands.Cascade.CascadeMove;

public class IntakeUp extends CommandGroup {

  /**
   * Add your docs here.
   */
  public IntakeUp() {
    addSequential(new IntakeHatchReload());
    addSequential(new IntakeRetract());
    addParallel(new CascadeMove(1));
  }
}