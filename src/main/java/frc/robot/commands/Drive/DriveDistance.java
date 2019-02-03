/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DrivetrainDistancePID;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class DriveDistance extends Command {

	private DrivetrainDistancePID pid;
	private SN_DoublePreference pref_timeout = new SN_DoublePreference("DriveDistance_timeout", 10.0);

	private double expireTime = 0.0;
	private String name;

	public DriveDistance(SN_DoublePreference inches, String commandName) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_drivetrain);

		pid = new DrivetrainDistancePID();
		pid.setSetpoint(inches);
		name = commandName;
	}

	public void setTimeout(SN_DoublePreference timeout) {
		pref_timeout = timeout;
	}

	public DrivetrainDistancePID getPID() {
		return pid;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.m_telemetry.setAutonomousStatus("Starting DriveDistance " + name + ": " + pid.getSetpoint() + " ");
		expireTime = timeSinceInitialized() + pref_timeout.getValue();

		Robot.m_drivetrain.resetEncoderCount();

		pid.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.m_telemetry.setAutonomousStatus("Executing DriveDistance " + name + ": " + pid.getSetpoint() + " ");
		double moveSpeed = pid.getOutput();

		Robot.m_drivetrain.arcadeDrive(moveSpeed, 0.0, false);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		boolean distanceTarget = pid.onRawTarget();
		double timeNow = timeSinceInitialized();

		boolean finished = (distanceTarget || (timeNow >= expireTime));

		return finished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.m_telemetry.setAutonomousStatus("Finishing DriveDistance " + name + ": " + pid.getSetpoint() + "");
		pid.disable();
		Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
