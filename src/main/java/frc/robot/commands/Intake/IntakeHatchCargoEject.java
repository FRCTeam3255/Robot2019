/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeHatchCargoEject extends Command {
	/**
	 * <ul>
	 * <li>Ejects hatches/cargo depending on if hatches/cargo are collected</li>
	 * <li>Stops spinning intake in end</li>
	 * </ul>
	 */
	double speed = 0.0;

	public IntakeHatchCargoEject() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_intake);
	}

	@Override
	protected void initialize() {
		if (!Robot.m_intake.isCargoCollected()) {
			Robot.m_intake.deployFinger();
		}
	}

	@Override
	protected void execute() {
		if (!Robot.m_intake.isHatchCollected()) {
			this.speed = Robot.m_oi.manipulatorStick.getDialAxis();

			if (speed < 0.25) {
				speed = 0.25;
			}
			Robot.m_intake.shootCargo(-speed);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
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