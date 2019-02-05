/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class ClimberLift extends Command {
  public ClimberLift(SN_DoublePreference setPoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_cascade);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_cascade.setLiftSpeed(RobotPreferences.CLIMBER_LIFT_SPEED.getValue());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
