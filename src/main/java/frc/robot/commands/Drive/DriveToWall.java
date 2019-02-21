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

public class DriveToWall extends Command {

  private static SN_DoublePreference wallSpeed = new SN_DoublePreference("wallSpeed", 0.5);
  private static SN_DoublePreference wallDecel = new SN_DoublePreference("wallDecel", 0.2);

  public DriveToWall() {
    requires(Robot.m_drivetrain);
    requires(Robot.m_navigation);
  }

  @Override
  protected void initialize() {
    Robot.m_drivetrain.arcadeDrive(wallSpeed.getValue(), 0.0);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_navigation.getAccelerationX() >= wallDecel.getValue();
  }

  @Override
  protected void end() {
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
