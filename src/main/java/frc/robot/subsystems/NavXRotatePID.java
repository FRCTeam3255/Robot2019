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
 * PID class based off the NavX yaw
 */
public class NavXRotatePID extends SN_PID {
	/**
	 * Creates a NavXRotatePID loop and sets PID values
	 */
	public NavXRotatePID() {
		super();

		setPID(RobotPreferences.YAW_P, RobotPreferences.YAW_I, RobotPreferences.YAW_D);
	}

	/**
	 * @return Inputs the yaw of the NavX
	 */
	@Override
	protected double returnPIDInput() {
		inputValid = true;
		return Robot.m_navigation.getYaw();
	}
}