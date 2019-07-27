/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Cascade;

public class CascadeManual extends Command {
  double speed = 0.0;
  double position = 0.0;

  public CascadeManual() {
    requires(Robot.m_cascade);
  }

  @Override
  protected void initialize() {
    position = Robot.m_cascade.getLiftEncoderCount();
    Robot.m_cascade.talonPid(position);
    Robot.m_telemetry.setCommandStatus("Starting CascadeLift" + ": " + Robot.m_cascade.talonPidError());

  }

  @Override
  protected void execute() {
    position += (100 * Robot.m_oi.manipulatorStick.getYAxis());

    Robot.m_cascade.talonPid(position);
    Robot.m_telemetry.setCommandStatus("Executing CascadeLift: " + Robot.m_cascade.talonPidError());

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_cascade.talonPid(position);
    Robot.m_telemetry.setCommandStatus("Finishing CascadeLift" + ": " + Robot.m_cascade.talonPidError());

    // Robot.m_cascade.lockCascade();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
