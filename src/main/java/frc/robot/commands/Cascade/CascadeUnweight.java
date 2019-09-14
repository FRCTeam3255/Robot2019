/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotPreferences;

public class CascadeUnweight extends Command {

	private double previousEncoder;

	public CascadeUnweight() {
		requires(Robot.m_cascade);
	}

	@Override
	protected void initialize() {
		Robot.m_telemetry.setCommandStatus("Starting CascadeUnweight");
		previousEncoder = Robot.m_cascade.getPosition();
	}

	@Override
	protected void execute() {
		Robot.m_cascade.setSpeed(.5);
		Robot.m_telemetry.setCommandStatus("Executing CascadeUnweight");
	}

	@Override
	protected boolean isFinished() {
		return (Robot.m_cascade.getPosition() >= (previousEncoder + 5));
	}

	@Override
	protected void end() {
		Robot.m_telemetry.setCommandStatus("Finishing CascadeUnweight");
		Robot.m_cascade.setSpeed(0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
