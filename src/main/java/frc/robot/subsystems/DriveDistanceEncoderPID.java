/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotPreferenceValues;

/**
 * Add your docs here.
 */
public class DriveDistanceEncoderPID extends DrivetrainDistancePID {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DriveDistanceEncoderPID() {
    super();

    this.preferencePName = RobotPreferenceValues.DRIVETRAIN_P_NAME;
    this.preferencePDefault = RobotPreferenceValues.DRIVETRAIN_P_DEFAULT;

    this.preferenceIName = RobotPreferenceValues.DRIVETRAIN_I_NAME;
    this.preferenceIDefault = RobotPreferenceValues.DRIVETRAIN_I_DEFAULT;

    this.preferenceDName = RobotPreferenceValues.DRIVETRAIN_D_NAME;
    this.preferenceDDefault = RobotPreferenceValues.DRIVETRAIN_D_DEFAULT;

    this.preferenceMaxChangeName = RobotPreferenceValues.DRIVETRAIN_MAX_CHANGE_NAME;
    this.preferenceMaxChangeDefault = RobotPreferenceValues.DRIVETRAIN_MAX_CHANGE_DEFAULT;

    this.preferenceMinName = RobotPreferenceValues.DRIVETRAIN_MIN_NAME;
    this.preferenceMinDefault = RobotPreferenceValues.DRIVETRAIN_MIN_DEFAULT;

    this.preferenceMaxName = RobotPreferenceValues.DRIVETRAIN_MAX_NAME;
    this.preferenceMaxDefault = RobotPreferenceValues.DRIVETRAIN_MAX_DEFAULT;

    this.preferenceTargetCountName = RobotPreferenceValues.DRIVETRAIN_TARGET_COUNT_NAME;
    this.preferenceTargetCountDefault = RobotPreferenceValues.DRIVETRAIN_TARGET_COUNT_DEFAULT;
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return Robot.m_drivetrain.getEncoderDistance();
  }
}