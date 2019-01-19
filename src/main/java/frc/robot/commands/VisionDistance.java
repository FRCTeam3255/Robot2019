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

public class VisionDistance extends Command {
  private double distance;
  private double angle;
  private double expireTime;
  public VisionDistance(double inches, double degrees) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_visionDistancePID);
    requires(Robot.m_visionRotatePID);
    requires(Robot.m_drivetrain);
    distance = inches;
    angle = degrees;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double timeout = RobotPreferences.VISION_TIMEOUT.get();
    expireTime = timeSinceInitialized() + timeout;

    double tolerance = RobotPreferences.VISION_TOLERANCE.get();
    double rotateTolerance = RobotPreferences.VISION_ROTATE_TOLERANCE.get();

    Robot.m_visionDistancePID.setRawTolerance(tolerance);
    Robot.m_visionRotatePID.setRawTolerance(rotateTolerance);
    Robot.m_visionDistancePID.setSetpoint(distance);
    Robot.m_visionRotatePID.setSetpoint(angle);
    Robot.m_visionDistancePID.enable();
    Robot.m_visionRotatePID.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double moveSpeed = Robot.m_visionDistancePID.getOutput();
    double rotateSpeed = Robot.m_visionRotatePID.getOutput();

    Robot.m_drivetrain.arcadeDrive(-moveSpeed, rotateSpeed, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean distanceTarget = Robot.m_visionDistancePID.onRawTarget();
    boolean rotateTarget = Robot.m_visionRotatePID.onRawTarget();
    double timeNow = timeSinceInitialized();

    boolean finished = (distanceTarget || rotateTarget || (timeNow >= expireTime));

    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_visionDistancePID.disable();
    Robot.m_visionRotatePID.disable();
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}