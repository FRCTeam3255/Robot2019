/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class DriveArcade extends Command {
	private static SN_DoublePreference frontSpeed = new SN_DoublePreference("frontSpeed", 1.0);
	private static SN_DoublePreference slowSpeedFactor = new SN_DoublePreference("slowSpeedFactor", 0.5);

	public DriveArcade() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double moveSpeed = Robot.m_oi.driverStick.getArcadeMove();
		double rotateSpeed = Robot.m_oi.driverStick.getArcadeRotate();

		if (Robot.m_oi.driverStick.btn_RBump.get()) {
			moveSpeed = moveSpeed * slowSpeedFactor.getValue();
			rotateSpeed = rotateSpeed * slowSpeedFactor.getValue();
		}
		Robot.m_drivetrain.arcadeDrive(moveSpeed, rotateSpeed, false);

		if (Robot.m_cascade.isClimberDeployed() && (moveSpeed < 0)) {
			Robot.m_drivetrain.setBackSpeed(frontSpeed.getValue());
		} else {
			Robot.m_drivetrain.setBackSpeed(0.0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.m_drivetrain.arcadeDrive(0.0, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}