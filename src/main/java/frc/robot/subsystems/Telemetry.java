/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.Cascade.*;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Drive.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Vision.*;
import frcteam3255.robotbase.Preferences.SN_Preferences;

/**
 * Subsystem containing board telemetry methoods
 */
public class Telemetry extends Subsystem {
	/**
	 * Put command buttons on the board
	 */
	public Telemetry() {
		// Cascade Commands
		SmartDashboard.putData("Lock Dogtooth", new CascadeLockDogtooth());
		SmartDashboard.putData("Unlock Dogtooth", new CascadeUnlockDogtooth());
		SmartDashboard.putData("Shift Cascade", new CascadeShiftTo());
		SmartDashboard.putData("Shift Climb", new ClimbShiftTo());
		SmartDashboard.putData("Reset Cascade Encoder", new CascadeResetEncoder());

		// Drivetrain Commands
		SmartDashboard.putData("Reset Drive Encoder", new DriveResetEncoder());
		SmartDashboard.putData("Drive To Wall", new DriveToWall());

		// Intake Commands
		SmartDashboard.putData("Deploy Intake", new IntakeDeploy());
		SmartDashboard.putData("Retract Intake", new IntakeRetract());
		SmartDashboard.putData("Deploy Hook", new IntakeHookDeploy());
		SmartDashboard.putData("Retract Hook", new IntakeHookRetract());
		SmartDashboard.putData("Eject", new IntakeHatchEject());
		SmartDashboard.putData("Ejector Retract", new IntakeEjectorRetract());

		// Climb Commands
		SmartDashboard.putData("Climb Deploy", new ClimbDeploy());
		SmartDashboard.putData("Climb Group", new ClimbGroup());

		// Lighting Commands

		// Navigation Commands
		SmartDashboard.putData("Reset Yaw", new DriveResetYaw());

		// Vision Commands
		SmartDashboard.putData("Set Driver Mode", new VisionSetDriverMode());
		SmartDashboard.putData("Set Vision Mode", new VisionSetVisionMode());
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

		// Intake Telemetry
		SmartDashboard.putBoolean("Intake Retracted", Robot.m_intake.isIntakeRetracted());
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
		SmartDashboard.putBoolean("Is Debug", !SN_Preferences.isUsingDefaults());
		SmartDashboard.putData("Running Commands", Scheduler.getInstance());
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
