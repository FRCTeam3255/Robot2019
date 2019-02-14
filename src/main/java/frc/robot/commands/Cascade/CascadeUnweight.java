/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class CascadeUnweight extends Command {

	private double previousEncoder;
	private static SN_DoublePreference cascadeUnweightSpeed = new SN_DoublePreference("cascadeUnweightSpeed", 0.5);
	private static SN_DoublePreference cascadeUnweightHeight = new SN_DoublePreference("cascadeUnweightHeight", 0.3);

	public CascadeUnweight() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_cascade);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.m_telemetry.setCommandStatus("Starting CascadeUnweight");
		previousEncoder = Robot.m_cascade.getLiftEncoderDistance();
		Robot.m_cascade.unlockCascade();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.m_telemetry.setCommandStatus("Executing CascadeUnweight");
		Robot.m_cascade.setLiftSpeed(cascadeUnweightSpeed.getValue());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.m_cascade.getLiftEncoderDistance() >= previousEncoder + cascadeUnweightHeight.getValue();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.m_telemetry.setCommandStatus("Finishing CascadeUnweight");
		Robot.m_cascade.setLiftSpeed(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
