/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.AutoPreferences;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frc.robot.commands.Cascade.CascadeLockDogtooth;
import frc.robot.commands.Cascade.CascadeResetEncoder;
import frc.robot.commands.Cascade.CascadeShiftTo;
import frc.robot.commands.Cascade.CascadeUnlockDogtooth;
import frc.robot.commands.Climber.ClimbDeploy;
import frc.robot.commands.Climber.ClimbShiftTo;
import frc.robot.commands.Drive.DriveDistance;
import frc.robot.commands.Drive.DriveResetEncoder;
import frc.robot.commands.Drive.DriveResetYaw;
import frc.robot.commands.Drive.DriveToWall;
import frc.robot.commands.Intake.IntakeCargoDeploy;
import frc.robot.commands.Intake.IntakeCargoRetract;
import frc.robot.commands.Intake.IntakeFingerDeploy;
import frc.robot.commands.Intake.IntakeFingerRetract;
import frc.robot.commands.Intake.IntakeHatchEject;
import frc.robot.commands.Intake.IntakeLinkageDeploy;
import frc.robot.commands.Intake.IntakeLinkageRetract;

/**
 * Subsystem containing board telemetry methoods
 */
public class Telemetry extends Subsystem {

	// PowerDistributionPanel pdp;

	/**
	 * Put command buttons on the board
	 */
	public Telemetry() {
		// pdp = new PowerDistributionPanel();
		// Cascade Commands
		SmartDashboard.putData("Lock Dogtooth", new CascadeLockDogtooth());
		SmartDashboard.putData("Unlock Dogtooth", new CascadeUnlockDogtooth());
		SmartDashboard.putData("Shift Cascade", new CascadeShiftTo());
		SmartDashboard.putData("Shift Climb", new ClimbShiftTo());
		SmartDashboard.putData("Reset Cascade Encoder", new CascadeResetEncoder());

		// Drivetrain Commands
		SmartDashboard.putData("Reset Drive Encoder", new DriveResetEncoder());
		SmartDashboard.putData("Drive To Wall", new DriveToWall());
		SmartDashboard.putData("Drive10kcounts", new DriveDistance(RobotPreferences.counts, "10000 Counts"));

		// Intake Commands
		SmartDashboard.putData("Deploy Cargo Intake", new IntakeCargoDeploy());
		SmartDashboard.putData("Retract Cargo Intake", new IntakeCargoRetract());
		SmartDashboard.putData("Deploy Linkage", new IntakeLinkageDeploy());
		SmartDashboard.putData("Retract Linkage", new IntakeLinkageRetract());

		SmartDashboard.putData("Deploy Finger", new IntakeFingerDeploy());
		SmartDashboard.putData("Retract Finger", new IntakeFingerRetract());
		SmartDashboard.putData("Eject", new IntakeHatchEject());

		// Climb Commands
		SmartDashboard.putData("Climb Deploy", new ClimbDeploy());

		// Lighting Commands

		// Navigation Commands
		SmartDashboard.putData("Reset Yaw", new DriveResetYaw());

	}

	/**
	 * When this methood is in a periodic methood in Robot.java, these values are
	 * updated
	 */
	public void update() {
		// Cascade Telemetry
		SmartDashboard.putNumber("Cascade Lift Count", Robot.m_cascade.getLiftEncoderCount());
		SmartDashboard.putNumber("Cascade Lift Distance", Robot.m_cascade.getLiftEncoderDistance());
		SmartDashboard.putBoolean("Cascade Top Switch", Robot.m_cascade.isTopSwitchClosed());
		SmartDashboard.putBoolean("Cascade Bottom Switch", Robot.m_cascade.isBottomSwitchClosed());
		SmartDashboard.putBoolean("Cascade Mode", Robot.m_cascade.isShiftedCascade());
		SmartDashboard.putNumber("Lift Speed", Robot.m_cascade.liftSpeed);

		// Drivetrain Telemetry
		SmartDashboard.putNumber("Drivetrain Encoder Count", Robot.m_drivetrain.getEncoderCount());
		SmartDashboard.putNumber("Drivetrain Encoder Distance", Robot.m_drivetrain.getEncoderDistance());
		SmartDashboard.putNumber("Drivetrain Error", Robot.m_drivetrain.pidError());
		// Intake Telemetry
		SmartDashboard.putBoolean("Cargo Intake Retracted", Robot.m_intake.isCargoIntakeRetracted());
		SmartDashboard.putBoolean("Linkage Retracted", Robot.m_intake.isLinkageRetracted());
		SmartDashboard.putBoolean("Hatch Collected", Robot.m_intake.isHatchCollected());
		SmartDashboard.putBoolean("Cargo Collected", Robot.m_intake.isCargoCollected());

		// Lighting Telemetry

		// Navigation Telemetry
		SmartDashboard.putBoolean("Is Calibrating", Robot.m_navigation.isCalibrating());
		SmartDashboard.putNumber("Yaw", Robot.m_navigation.getYaw());
		SmartDashboard.putNumber("Acceleration X", Robot.m_navigation.getAccelerationX());
		SmartDashboard.putNumber("Acceleration Y", Robot.m_navigation.getAccelerationY());
		SmartDashboard.putNumber("Acceleration Z", Robot.m_navigation.getAccelerationZ());

		// Vision Telemetry
		SmartDashboard.putBoolean("Target Found", Robot.m_vision.targetFound());
		SmartDashboard.putNumber("Vision Distance From Target", Robot.m_vision.getDistance());
		SmartDashboard.putNumber("Vision Rotation", Robot.m_vision.getRotation());
		SmartDashboard.putNumber("Vision Horizontal Offset", Robot.m_vision.getHorizontalOffset());
		SmartDashboard.putNumber("Vision Width", Robot.m_vision.getWidth());

		// Other Telemetry
		SmartDashboard.putString("get Side", AutoPreferences.getSide());
		SmartDashboard.putString("get position", AutoPreferences.getPosition());

	}

	@Override
	public void initDefaultCommand() {
	}

	/**
	 * Updates the status of autonomous commands onto the board
	 * 
	 * @param autonomousState The current autonomous operation happening
	 */
	public void setCommandStatus(String commandState) {
		SmartDashboard.putString("Command Status", commandState);
	}
}
