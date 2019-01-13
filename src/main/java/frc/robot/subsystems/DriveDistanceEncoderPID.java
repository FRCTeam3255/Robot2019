/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frcteam3255.robotbase.SN_PID;

/**
 * Add your docs here.
 */
public class DriveDistanceEncoderPID extends SN_PID {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DriveDistanceEncoderPID() {
    super("DrivetrainDistancePID");

    this.preferenceP = RobotPreferences.DRIVETRAIN_P;
    this.preferenceI = RobotPreferences.DRIVETRAIN_I;
    this.preferenceD = RobotPreferences.DRIVETRAIN_D;

    this.preferenceMaxChange = RobotPreferences.DRIVETRAIN_MAX_CHANGE;
    this.preferenceMin = RobotPreferences.DRIVETRAIN_MIN;
    this.preferenceMax = RobotPreferences.DRIVETRAIN_MAX;

    this.preferenceTargetCount = RobotPreferences.DRIVETRAIN_TARGET_COUNT;
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return Robot.m_drivetrain.getEncoderDistance();
  }
}