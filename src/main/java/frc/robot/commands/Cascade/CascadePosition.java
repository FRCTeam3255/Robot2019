/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Intake.fieldHeights;

public class CascadePosition extends Command {

	private fieldHeights setpoint;

	public CascadePosition(fieldHeights position) {
		requires(Robot.m_cascade);
		setpoint = position;
	}

	@Override
	protected void initialize() {

		Robot.m_cascade.talonPid(Robot.m_intake.getSetpoint(setpoint).getValue());

		Robot.m_telemetry.setCommandStatus("Starting CascadeLift" + ": " + Robot.m_cascade.talonPidError());
	}

	@Override
	protected void execute() {
		Robot.m_cascade.talonPid(Robot.m_intake.getSetpoint(setpoint).getValue());

		Robot.m_telemetry.setCommandStatus("Executing CascadeLift: " + Robot.m_cascade.talonPidError());
	}

	@Override
	protected boolean isFinished() {
		if (Robot.m_cascade.talonPidError() > -30 && Robot.m_cascade.talonPidError() < 30) {
			return true;
		}

		if (Robot.m_cascade.isTopSwitchClosed() || Robot.m_cascade.isBottomSwitchClosed()) {
			return true;
		}
		return false;

	}

	@Override
	protected void end() {
		Robot.m_telemetry.setCommandStatus("Finishing CascadeLift" + ": " + Robot.m_cascade.talonPidError());
		Robot.m_cascade.setLiftSpeed(0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}