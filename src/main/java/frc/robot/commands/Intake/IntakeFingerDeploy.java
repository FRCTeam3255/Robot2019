/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class IntakeFingerDeploy extends InstantCommand {
	/**
	 * Deploys hatch finger
	 */
	public IntakeFingerDeploy() {
		super();
		requires(Robot.m_intake);
	}

	@Override
	protected void initialize() {
		Robot.m_intake.deployFinger();
	}

}
