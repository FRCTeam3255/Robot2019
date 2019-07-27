/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frcteam3255.robotbase.Preferences.SN_IntPreference;

public class DriveDistance extends Command {
  String name = null;
  double setpoint;

  public DriveDistance(SN_IntPreference inches, String string) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);
    name = string;
    setpoint = inches.getValue();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Robot.m_drivetrain.resetEncoderCount();
    Robot.m_drivetrain.pid(setpoint);
    Robot.m_telemetry.setCommandStatus("Starting DriveDistance " + name + ": " + Robot.m_drivetrain.pidError() + " ");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_telemetry.setCommandStatus("Executing DriveDistance: " + Robot.m_drivetrain.pidError());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    if (Robot.m_drivetrain.pidError() > 1 && Robot.m_drivetrain.pidError() < 1) {
      return true;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.talonReset();
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
    Robot.m_telemetry.setCommandStatus("Ended DriveDistance: " + Robot.m_drivetrain.pidError());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
