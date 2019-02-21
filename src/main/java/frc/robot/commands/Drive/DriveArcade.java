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
		requires(Robot.m_drivetrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double moveSpeed = Robot.m_oi.driverStick.getArcadeMove();
		double rotateSpeed = Robot.m_oi.driverStick.getArcadeRotate();

		if (Robot.m_oi.driverStick.btn_RBump.get()) {
			moveSpeed = moveSpeed * RobotPreferences.SLOW_SPEED_MOVE_FACTOR.getValue();
			rotateSpeed = rotateSpeed * RobotPreferences.SLOW_SPEED_ROTATE_FACTOR.getValue();
		} else {
			moveSpeed = moveSpeed * RobotPreferences.HIGH_SPEED_MOVE_FACTOR.getValue();
			rotateSpeed = rotateSpeed * RobotPreferences.HIGH_SPEED_ROTATE_FACTOR.getValue();
		}
		Robot.m_drivetrain.arcadeDrive(moveSpeed, rotateSpeed);

		if (Robot.m_cascade.isShiftedClimb() && (moveSpeed < 0)) {
			Robot.m_drivetrain.enableClimbDrive();
		} else {
			Robot.m_drivetrain.disableClimbDrive();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.m_drivetrain.arcadeDrive(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}