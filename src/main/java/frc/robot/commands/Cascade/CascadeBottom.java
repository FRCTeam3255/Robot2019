/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotPreferences;

public class CascadeBottom extends Command {
  public CascadeBottom() {
    requires(Robot.m_cascade);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_cascade.setLiftSpeed(RobotPreferences.CASCADE_BOTTOM_SPEED.getValue());
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_cascade.isBottomSwitchClosed();
  }

  @Override
  protected void end() {
    Robot.m_cascade.setLiftSpeed(0.0);
    Robot.m_cascade.resetLiftEncoder();
  }

  @Override
  protected void interrupted() {
    Robot.m_cascade.setLiftSpeed(0.0);
  }
}
