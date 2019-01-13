/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frcteam3255.robotbase.RobotPreferences;


public class DrivetrainDistance extends Command {
  private double distance;
  private double expireTime;
  public DrivetrainDistance(double inches) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveDistanceEncoderPID);
    requires(Robot.m_drivetrain);
    distance = inches;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.err.println("DrivetrainDistance.initialize");

    expireTime = timeSinceInitialized() + RobotPreferences.timeOut();

    Robot.m_drivetrain.resetEncoderCount();
    Robot.m_driveDistanceEncoderPID.setRawTolerance(RobotPreferences.drivetrainTolerance());
    Robot.m_driveDistanceEncoderPID.setSetpoint(distance);
    Robot.m_driveDistanceEncoderPID.enable();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double moveSpeed = Robot.m_driveDistanceEncoderPID.getOutput();

    System.err.println("DrivetrainDistance.execute: PID output = " + moveSpeed);
    Robot.m_drivetrain.arcadeDrive(Robot.m_driveDistanceEncoderPID.getOutput(), 0.0, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean distanceTarget = Robot.m_driveDistanceEncoderPID.onRawTarget();
    double timeNow = timeSinceInitialized();

    boolean finished = (distanceTarget || (timeNow >= expireTime));

    if(finished) {
      System.err.println("DrivetrainDistance.isFinished: finishing");
    }

    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_driveDistanceEncoderPID.disable();
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}