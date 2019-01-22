/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CascadeLiftAnInch extends Command {

  private double previousEncoder;

  public CascadeLiftAnInch() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_cascade);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    previousEncoder = Robot.m_cascade.getLiftEncoderCount();
    Robot.m_cascade.unlockCascade();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_cascade.setLiftSpeed(0.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_cascade.getLiftEncoderCount() >= previousEncoder + 1;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_cascade.setLiftSpeed(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
