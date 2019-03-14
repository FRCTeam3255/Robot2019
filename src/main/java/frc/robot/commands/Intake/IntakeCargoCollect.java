/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeCargoCollect extends Command {
	public IntakeCargoCollect() {
		requires(Robot.m_intake);
	}

	@Override
	protected void initialize() {
		Robot.m_intake.retractFinger();
	}

	@Override
	protected void execute() {
		Robot.m_intake.collectCargo();
	}

	@Override
	protected boolean isFinished() {
		return Robot.m_intake.isCargoCollected();
	}

	@Override
	protected void end() {
		Robot.m_intake.holdCargo();

	}

	@Override
	protected void interrupted() {
		end();
	}
}