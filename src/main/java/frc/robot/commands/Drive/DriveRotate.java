/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveRotate extends Command {
  double setpoint = 0;
  double rotateP = 0;

  public DriveRotate(double goal, double p) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);
    rotateP = p;
    setpoint = goal;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drivetrain.rotationPid(Robot.m_navigation.getYaw(), setpoint, rotateP);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drivetrain.rotationPid(Robot.m_navigation.getYaw(), setpoint, rotateP);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Robot.m_navigation.getYaw() == setpoint);

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.talonReset();
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
