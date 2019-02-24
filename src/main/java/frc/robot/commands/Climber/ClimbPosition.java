/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CascadePID;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class ClimbPosition extends Command {

  private CascadePID pid;
  private SN_DoublePreference pref_timeout = new SN_DoublePreference("Lift Timeout", 100.0);
  private SN_DoublePreference setpoint = new SN_DoublePreference("ClimbSetpoint", 0.0);
  private double expireTime = 0.0;
  private double speed = 0;

  public ClimbPosition(SN_DoublePreference climbSetpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_cascade);
    setpoint = climbSetpoint;
    pid = new CascadePID();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_cascade.shiftClimb();
    expireTime = timeSinceInitialized() + pref_timeout.getValue();
    pid.setSetpoint(setpoint);
    pid.enable();

    Robot.m_telemetry.setCommandStatus("Starting ClimbPosition" + ": " + pid.getSetpoint());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_telemetry.setCommandStatus("Executing ClimbPosition");
    speed = pid.getOutput();
    Robot.m_cascade.setLiftSpeed(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean distanceTarget = pid.onRawTarget();
    double timeNow = timeSinceInitialized();
    boolean finished = (distanceTarget || (timeNow >= expireTime)
        || ((speed > 0) && Robot.m_cascade.isTopSwitchClosed())
        || ((speed < 0) && Robot.m_cascade.isBottomSwitchClosed()));

    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_telemetry.setCommandStatus("Finishing ClimbPosition" + ": " + pid.getSetpoint());
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
