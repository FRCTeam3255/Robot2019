/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Lighting;

public class IntakeWaitForHatch extends Command {
  public IntakeWaitForHatch() {
    requires(Robot.m_intake);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_lighting.setLighting(Lighting.VIOLET);
  }

  @Override
  protected boolean isFinished() {
    return (Robot.m_intake.isHatchCollected() && (Robot.m_intake.isHookDeployed()));
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
