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

  private static SN_DoublePreference defaultWallSpeed = new SN_DoublePreference("wallSpeed", 0.5);
  private static SN_DoublePreference defaultWallDecel = new SN_DoublePreference("wallDecel", 0.2);
  private static SN_DoublePreference wallSpeed;
  private static SN_DoublePreference wallDecel;

  public DriveToWall(SN_DoublePreference speed, SN_DoublePreference decel) {
    requires(Robot.m_drivetrain);
    requires(Robot.m_navigation);
    wallSpeed = speed;
    wallDecel = decel;
  }

  public DriveToWall() {
    this(defaultWallSpeed, defaultWallDecel);
  }

  @Override
  protected void initialize() {
    Robot.m_drivetrain.arcadeDrive(wallSpeed.getValue(), 0.0, false);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    if (wallSpeed.getValue() > 0.0) {
      return Robot.m_navigation.getAccelerationX() >= wallDecel.getValue();
    } else {
      return Robot.m_navigation.getAccelerationX() <= -wallDecel.getValue();
    }
  }

  @Override
  protected void end() {
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
