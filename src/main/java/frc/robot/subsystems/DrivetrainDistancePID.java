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
 * PID class based off the encoder distance
 */
public class DrivetrainDistancePID extends SN_PID {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	/**
	 * Creates a DrivetrainDistancePID loop and sets PID values
	 */
	public DrivetrainDistancePID() {
		super();

		setPID(RobotPreferences.DRIVETRAIN_P, RobotPreferences.DRIVETRAIN_I, RobotPreferences.DRIVETRAIN_D);
		setTolerance(RobotPreferences.DRIVETRAIN_TOLERANCE);
	}

	/**
	 * @return Inputs the drivetrain encoder distance
	 */
	@Override
	protected double returnPIDInput() {
		inputValid = true;
		return Robot.m_drivetrain.getEncoderDistance();
	}
}