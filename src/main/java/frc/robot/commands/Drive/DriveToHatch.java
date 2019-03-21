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

  private static SN_DoublePreference hatchSpeed = new SN_DoublePreference("hatchSpeed", 0.3);

  public DriveToHatch() {

    requires(Robot.m_drivetrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_drivetrain.arcadeDrive(hatchSpeed.getValue(), 0.0, false);
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_intake.isHatchCollected();
  }

  @Override
  protected void end() {
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
    Robot.m_intake.retractFinger();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
