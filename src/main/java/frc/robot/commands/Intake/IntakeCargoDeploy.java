/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class IntakeCargoDeploy extends InstantCommand {
	/**
	 * Deploys cargo intake
	 */
	public IntakeCargoDeploy() {
		super();
		requires(Robot.m_intake);
	}

	@Override
	protected void initialize() {
		Robot.m_intake.deployCargoIntake();
	}
}