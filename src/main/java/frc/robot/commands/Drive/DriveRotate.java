/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.NavXRotatePID;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class DriveRotate extends Command {

	private NavXRotatePID pid;
	private SN_DoublePreference pref_timeout = new SN_DoublePreference("DriveRotate_timeout", 10.0);

	double expireTime = 0.0;

	public DriveRotate(double degrees) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_drivetrain);
		requires(Robot.m_navigation);

		pid = new NavXRotatePID();
		pid.setSetpoint(degrees);
	}

	public void setTimeout(SN_DoublePreference timeout) {
		pref_timeout = timeout;
	}

	public NavXRotatePID getPID() {
		return pid;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		expireTime = timeSinceInitialized() + pref_timeout.getValue();

		Robot.m_navigation.resetYaw();

		pid.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double rotateSpeed = pid.getOutput();

		Robot.m_drivetrain.arcadeDrive(0.0, rotateSpeed);
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
		pid.disable();
		Robot.m_drivetrain.arcadeDrive(0.0, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
