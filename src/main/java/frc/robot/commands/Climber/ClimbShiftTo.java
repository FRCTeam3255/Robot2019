/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ClimbShiftTo extends InstantCommand {
	/**
	 * Shifts to climber mode
	 */
	public ClimbShiftTo() {
		super();
		requires(Robot.m_cascade);
		requires(Robot.m_intake);
	}

	@Override
	protected void initialize() {
		Robot.m_cascade.shiftClimb();
	}

}
