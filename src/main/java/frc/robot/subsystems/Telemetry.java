/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frcteam3255.robotbase.SN_Preferences;

/**
 * Add your docs here.
 */
public class Telemetry extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Telemetry() {
  }

  public void update() {
    SmartDashboard.putBoolean("Is Debug", !SN_Preferences.isUsingDefaults());
    SmartDashboard.putNumber("Drivetrain Encoder Count", Robot.m_drivetrain.getEncoderCount());
    SmartDashboard.putNumber("Drivetrain Encoder Distance", Robot.m_drivetrain.getEncoderDistance());
    SmartDashboard.putNumber("Vision Distance From Target", Robot.m_vision.getDistance());
    SmartDashboard.putNumber("Vision Rotation", Robot.m_vision.getRotation());
    SmartDashboard.putNumber("Vision Horizontal Offset", Robot.m_vision.getHorizontalOffset());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
