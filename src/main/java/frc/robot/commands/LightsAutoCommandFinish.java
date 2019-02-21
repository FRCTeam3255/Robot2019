/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Lighting;

public class LightsAutoCommandFinish extends InstantCommand {
  public LightsAutoCommandFinish() {
    super();
  }

  @Override
  protected void initialize() {
    Robot.m_lighting.setLighting(Lighting.HOT_PINK);
  }

}
