/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotPreferences;

public class DriveArcade extends Command {
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
			moveSpeed = moveSpeed * RobotPreferences.slowSpeedMoveFactor.getValue();
			rotateSpeed = rotateSpeed * RobotPreferences.slowSpeedRotateFactor.getValue();
		} else {
			moveSpeed = moveSpeed * RobotPreferences.highSpeedMoveFactor.getValue();
			rotateSpeed = rotateSpeed * RobotPreferences.highSpeedRotateFactor.getValue();
		}
		Robot.m_drivetrain.arcadeDrive(moveSpeed, rotateSpeed);

		if (Robot.m_cascade.isShiftedClimb() && (moveSpeed < 0)) {
			Robot.m_drivetrain.enableClimbDrive();
		} else {
			Robot.m_drivetrain.disableClimbDrive();
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