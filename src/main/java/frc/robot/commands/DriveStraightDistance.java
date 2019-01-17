/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotPreferences;

public class DriveStraightDistance extends Command {
private double distance;
private double expireTime;
  public DriveStraightDistance(double inches) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_distanceEncoderPID);
    requires(Robot.m_drivetrain);
    requires(Robot.m_navigation);
    inches = distance;
  }

// Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double timeout = RobotPreferences.DRIVETRAIN_TIMEOUT.get();
    expireTime = timeSinceInitialized() + timeout;

    Robot.m_drivetrain.resetEncoderCount();
    Robot.m_navigation.resetYaw();

    double tolerance = RobotPreferences.DRIVETRAIN_TARGET_TOLERANCE.get();
    double yawTolerance = RobotPreferences.YAW_TARGET_TOLERANCE.get();

    Robot.m_distanceEncoderPID.setRawTolerance(tolerance);
    Robot.m_distanceEncoderPID.setSetpoint(distance);
    Robot.m_yawPID.setRawTolerance(yawTolerance);
    Robot.m_yawPID.setSetpoint(0.0);

    Robot.m_distanceEncoderPID.enable();
    Robot.m_yawPID.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double moveSpeed = Robot.m_distanceEncoderPID.getOutput();
    double rotateSpeed = Robot.m_yawPID.getOutput();

    Robot.m_drivetrain.arcadeDrive(moveSpeed, rotateSpeed, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean distanceTarget = Robot.m_distanceEncoderPID.onRawTarget();
    double timeNow = timeSinceInitialized();

    boolean finished = (distanceTarget || (timeNow >= expireTime));

    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_distanceEncoderPID.disable();
    Robot.m_yawPID.disable();
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
