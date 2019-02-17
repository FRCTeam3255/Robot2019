/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class DriveToHatch extends Command {

  private static SN_DoublePreference hatchSpeed = new SN_DoublePreference("wallSpeed", 0.5);

  public DriveToHatch() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);
    requires(Robot.m_navigation);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drivetrain.arcadeDrive(hatchSpeed.getValue(), 0.0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_intake.isHatchCollected();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0);
    Robot.m_intake.retractHook();
    if (Robot.m_intake.isHatchCollected()) {
      Robot.m_lighting.setLighting(Robot.m_lighting.lightsHatch.getValue());
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
