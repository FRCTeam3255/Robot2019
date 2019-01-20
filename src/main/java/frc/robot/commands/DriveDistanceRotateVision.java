/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.VisionDistancePID;
import frc.robot.subsystems.VisionRotatePID;
import frcteam3255.robotbase.SN_DoublePreference;

public class DriveDistanceRotateVision extends Command {
  
  private VisionDistancePID distancePID;
  private VisionRotatePID rotatePID;
  private SN_DoublePreference pref_timeout = new SN_DoublePreference("VisionRotateDistance_timeout", 10.0);

  private double expireTime = 0.0;

  public DriveDistanceRotateVision(SN_DoublePreference inches, SN_DoublePreference degrees) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);

    distancePID = new VisionDistancePID();
    rotatePID = new VisionRotatePID();

    distancePID.setSetpoint(inches);
    rotatePID.setSetpoint(degrees);
  }

  public void setTimeout(SN_DoublePreference timeout){
    pref_timeout = timeout;
  }

  public VisionDistancePID getDistancePID() {
    return distancePID;
  }

  public VisionRotatePID getYawPID() {
    return rotatePID;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    expireTime = timeSinceInitialized() + pref_timeout.get();
    
    distancePID.enable();
    rotatePID.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double moveSpeed = distancePID.getOutput();
    double rotateSpeed = rotatePID.getOutput();

    Robot.m_drivetrain.arcadeDrive(-moveSpeed, rotateSpeed, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean distanceTarget = distancePID.onRawTarget();
    boolean rotateTarget = rotatePID.onRawTarget();
    double timeNow = timeSinceInitialized();

    boolean finished = (distanceTarget || rotateTarget || (timeNow >= expireTime));

    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    distancePID.disable();
    rotatePID.disable();
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}