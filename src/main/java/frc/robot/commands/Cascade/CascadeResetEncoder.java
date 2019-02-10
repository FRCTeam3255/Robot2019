/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class CascadeResetEncoder extends Command {
	/**
	 * Add your docs here.
	 */
	public CascadeResetEncoder() {
		super();
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_cascade);
	}

	// Called once when the command executes
	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		if (Robot.m_cascade.isBottomSwitchClosed() && (Robot.m_cascade.isShiftedCascade())) {
			Robot.m_cascade.resetLiftEncoder();
		}

	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}