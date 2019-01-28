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
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class CascadeLift extends Command {

  private CascadePID pid;
  private SN_DoublePreference pref_timeout = new SN_DoublePreference("Lift Timeout", 10.0);
  private double expireTime = 0.0;
  private double distance;

  public CascadeLift(SN_DoublePreference inches) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_cascade);

    pid = new CascadePID();
    pid.setSetpoint(inches);
    distance = inches.getValue();
  }

  public void setTimeout(SN_DoublePreference timeout) {
    pref_timeout = timeout;
  }

  public CascadePID getPID() {
    return pid;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_telemetry.setAutonomousStatus("Starting CascadeLift" + ": " + distance);
    expireTime = timeSinceInitialized() + pref_timeout.getValue();

    Robot.m_cascade.resetLiftEncoder();

    pid.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_telemetry.setAutonomousStatus("Executing CascadeLift" + ": " + distance);
    double moveSpeed = pid.getOutput();

    Robot.m_cascade.setLiftSpeed(moveSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean distanceTarget = pid.onRawTarget();
    double timeNow = timeSinceInitialized();
    boolean finished = (distanceTarget || (timeNow >= expireTime));

    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_telemetry.setAutonomousStatus("Finishing CascadeLift" + ": " + distance);
    pid.disable();
    Robot.m_cascade.setLiftSpeed(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
