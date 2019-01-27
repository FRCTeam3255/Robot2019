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
public class NavXRotatePID extends SN_PID {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public NavXRotatePID() {
    super();

    setPID(RobotPreferences.YAW_P, RobotPreferences.YAW_I, RobotPreferences.YAW_D);
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    inputValid = true;
    return Robot.m_navigation.getYaw();
  }
}