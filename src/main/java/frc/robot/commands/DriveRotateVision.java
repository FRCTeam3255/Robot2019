/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.VisionRotatePID;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class DriveRotateVision extends Command {

  private VisionRotatePID pid;
  private SN_DoublePreference pref_timeout = new SN_DoublePreference("VisionRotate_timeout", 10.0);

  private double expireTime = 0.0;
  private double angle;

  public DriveRotateVision(SN_DoublePreference degrees) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);

    pid = new VisionRotatePID();
    pid.setSetpoint(degrees);
    angle = degrees.get();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_telemetry.setAutonomousStatus("Starting DriveRotateVision" + ": " + angle);
    expireTime = timeSinceInitialized() + pref_timeout.get();

   pid.enable();
  }

  public void setTimeout(SN_DoublePreference timeout){
    pref_timeout = timeout;
  }

  public VisionRotatePID getPID() {
    return pid;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_telemetry.setAutonomousStatus("Executing DriveRotateVision" + ": " + angle);
    double rotateSpeed = 0.0;

    Robot.m_drivetrain.arcadeDrive(0.0, rotateSpeed, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean rotateTarget =pid.onRawTarget();
    double timeNow = timeSinceInitialized();

    boolean finished = (rotateTarget || (timeNow >= expireTime));

    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_telemetry.setAutonomousStatus("Finishing DriveRotateVision" + ": " + angle);
   pid.disable();
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}