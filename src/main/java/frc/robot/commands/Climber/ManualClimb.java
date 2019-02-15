/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualClimb extends Command {

  double speed = 0.0;

  public ManualClimb() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);\
    requires(Robot.m_cascade);
    requires(Robot.m_intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    speed = 0.0;
    Robot.m_cascade.shiftClimb();
    Robot.m_cascade.unlockCascade();
    // Robot.m_cascade.deployClimb();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    speed = Robot.m_oi.manipulatorStick.getYAxis();
    Robot.m_cascade.setLiftSpeed(speed);
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