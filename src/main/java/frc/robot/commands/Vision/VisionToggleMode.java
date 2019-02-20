/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class VisionToggleMode extends InstantCommand {
  public VisionToggleMode() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_vision);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_vision.toggleMode();
  }
}