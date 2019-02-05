/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotPreferences;

public class DriveUntilDeceleration extends Command {
  public DriveUntilDeceleration() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drivetrain.arcadeDrive(RobotPreferences.DECELERATION_SPEED.getValue(), 0, true);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_navigation.isDecelerated();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, true);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
