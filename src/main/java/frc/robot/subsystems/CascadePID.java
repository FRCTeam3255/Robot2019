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
 * PID class based off the cascade distance
 */
public class CascadePID extends SN_PID {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  /**
   * Creates a Cascade PID loop and sets PID values
   */
  public CascadePID() {
    super();

    setPID(RobotPreferences.CASCADE_P, RobotPreferences.CASCADE_I, RobotPreferences.CASCADE_D);
  }

  /**
   * @return Inputs the cascade distance
   */
  @Override
  protected double returnPIDInput() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    inputValid = true;
    double d = Robot.m_cascade.getLiftEncoderDistance();
    System.err.println("CascadePID.returnPIDInput: d = " + d);
    return d;
  }
}
