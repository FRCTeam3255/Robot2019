/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class DriveDistanceEncoderPID extends DrivetrainDistancePID {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DriveDistanceEncoderPID() {
    super();
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return Robot.m_drivetrain.getEncoderDistance();
  }
}