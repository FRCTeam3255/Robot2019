/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class CascadeUnlockDogtooth extends InstantCommand {
	/**
	 * Unlocks cascade dogtooth
	 */
	public CascadeUnlockDogtooth() {
		super();
		requires(Robot.m_cascade);
	}

	@Override
	protected void initialize() {
		Robot.m_cascade.unlockCascade();
	}

}
