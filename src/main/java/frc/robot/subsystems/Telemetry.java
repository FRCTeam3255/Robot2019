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
import frc.robot.commands.VisionSetDriverMode;
import frc.robot.commands.VisionSetVisionMode;
import frc.robot.commands.Drive.DriveResetEncoder;
import frc.robot.commands.Drive.DriveResetYaw;
import frcteam3255.robotbase.Preferences.SN_Preferences;

/**
 * Subsystem containing board telemetry methoods
 */
public class Telemetry extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  /**
   * Put command buttons on the board
   */
  public Telemetry() {
    SmartDashboard.putData("Reset Encoder", new DriveResetEncoder());
    SmartDashboard.putData("Reset Yaw", new DriveResetYaw());
    SmartDashboard.putData("Set Driver Mode", new VisionSetDriverMode());
    SmartDashboard.putData("Set Vision Mode", new VisionSetVisionMode());
  }

  /**
   * When this methood is in a periodic methood in Robot.java, these values are
   * updated
   */
  public void update() {
    SmartDashboard.putBoolean("Is Debug", !SN_Preferences.isUsingDefaults());
    SmartDashboard.putNumber("Drivetrain Encoder Count", Robot.m_drivetrain.getEncoderCount());
    SmartDashboard.putNumber("Drivetrain Encoder Distance", Robot.m_drivetrain.getEncoderDistance());
    SmartDashboard.putNumber("Vision Distance From Target", Robot.m_vision.getDistance());
    SmartDashboard.putNumber("Vision Rotation", Robot.m_vision.getRotation());
    SmartDashboard.putNumber("Vision Horizontal Offset", Robot.m_vision.getHorizontalOffset());
    SmartDashboard.putNumber("Yaw", Robot.m_navigation.getYaw());
    SmartDashboard.putBoolean("Target Found", Robot.m_vision.targetFound());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * Updates the status of autonomous commands onto the board
   * 
   * @param autonomousState The current autonomous operation happening
   */
  public void setAutonomousStatus(String autonomousState) {
    SmartDashboard.putString("Autonomous Status", autonomousState);
  }

}
