/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CascadePID;

public class CascadeManual extends Command {
  double speed = 0.0;

  public CascadeManual() {
    requires(Robot.m_cascade);
  }

  @Override
  protected void initialize() {
    speed = 0.0;
  }

  @Override
  protected void execute() {
    speed = Robot.m_oi.manipulatorStick.getYAxis();
    if (speed > CascadePID.CASCADE_MAXOUTUP.getValue()) {
      speed = CascadePID.CASCADE_MAXOUTUP.getValue();
    } else if (speed < -CascadePID.CASCADE_MAXOUTDOWN.getValue()) {
      speed = -CascadePID.CASCADE_MAXOUTDOWN.getValue();
    }
    Robot.m_cascade.setLiftSpeed(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_cascade.setLiftSpeed(0.0);
    Robot.m_cascade.lockCascade();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
