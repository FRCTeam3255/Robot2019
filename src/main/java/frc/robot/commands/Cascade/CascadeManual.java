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
    speed = Robot.m_oi.manipulatorStick.getYAxis();
    if (speed > RobotPreferences.CASCADE_MAXOUTUP.getValue()) {
      speed = RobotPreferences.CASCADE_MAXOUTUP.getValue();
    } else if (speed < -RobotPreferences.CASCADE_MAXOUTDOWN.getValue()) {
      speed = -RobotPreferences.CASCADE_MAXOUTDOWN.getValue();
    }
    Robot.m_cascade.setLiftSpeed(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_cascade.talonPid(Robot.m_cascade.getLiftEncoderCount());
    Robot.m_telemetry.setCommandStatus("Finishing CascadeLift" + ": " + Robot.m_cascade.talonPidError());
  }

  @Override
  protected void interrupted() {
    end();
  }
}
