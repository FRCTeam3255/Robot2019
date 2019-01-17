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

public class DriveRotate extends Command {
private double angle;
private double expireTime;
  public DriveRotate(double degrees) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);
    requires(Robot.m_navigation);
    degrees = angle;
  }

// Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double timeout = RobotPreferences.DRIVETRAIN_TIMEOUT.get();
    expireTime = timeSinceInitialized() + timeout;

    Robot.m_navigation.resetYaw();

    double yawTolerance = RobotPreferences.YAW_TARGET_TOLERANCE.get();

    Robot.m_yawPID.setRawTolerance(yawTolerance);
    Robot.m_yawPID.setSetpoint(angle);

    Robot.m_yawPID.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double rotateSpeed = Robot.m_yawPID.getOutput();

    Robot.m_drivetrain.arcadeDrive(0.0, rotateSpeed, false);
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
