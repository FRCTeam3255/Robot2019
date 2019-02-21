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

	@Override
	protected void initialize() {
		Robot.m_telemetry.setCommandStatus("Starting DriveDistance " + name + ": " + pid.getSetpoint() + " ");
		expireTime = timeSinceInitialized() + pref_timeout.getValue();

		Robot.m_drivetrain.resetEncoderCount();

		pid.enable();
	}

	@Override
	protected void execute() {
		Robot.m_telemetry.setCommandStatus("Executing DriveDistance " + name + ": " + pid.getSetpoint() + " ");
		double moveSpeed = pid.getOutput();

		Robot.m_drivetrain.arcadeDrive(moveSpeed, 0.0);

		if (Robot.m_cascade.isShiftedClimb() && (moveSpeed < 0.0)) {
			Robot.m_drivetrain.enableClimbDrive();
		} else {
			Robot.m_drivetrain.disableClimbDrive();
		}
	}

	@Override
	protected boolean isFinished() {
		boolean distanceTarget = pid.onRawTarget();
		double timeNow = timeSinceInitialized();

		boolean finished = (distanceTarget || (timeNow >= expireTime));

		return finished;
	}

	@Override
	protected void end() {
		Robot.m_telemetry.setCommandStatus("Finishing DriveDistance " + name + ": " + pid.getSetpoint() + "");
		pid.disable();
		Robot.m_drivetrain.arcadeDrive(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
