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
import frc.robot.subsystems.CascadePID;

public class CascadeManual extends Command {
  double speed = 0.0;

  public CascadeManual() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_cascade);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    speed = 0.0;
  }

  // Called repeatedly when this Command is scheduled to run
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

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return ((speed > 0) && Robot.m_cascade.isTopSwitchClosed());
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_cascade.setLiftSpeed(0.0);
    Robot.m_cascade.lockCascade();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
