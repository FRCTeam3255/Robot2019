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
import frc.robot.commands.Cascade.CascadeDeployClimb;
import frc.robot.commands.Cascade.CascadeLockDogtooth;
import frc.robot.commands.Cascade.CascadeResetEncoder;
import frc.robot.commands.Cascade.CascadeRetractClimb;
import frc.robot.commands.Cascade.CascadeShiftTo;
import frc.robot.commands.Cascade.CascadeUnlockDogtooth;
import frc.robot.commands.Climber.ClimbShiftTo;
import frc.robot.commands.Drive.DriveResetEncoder;
import frc.robot.commands.Drive.DriveResetYaw;
import frc.robot.commands.Drive.DriveToWall;
import frc.robot.commands.Intake.IntakeDeploy;
import frc.robot.commands.Intake.IntakeDeployHook;
import frc.robot.commands.Intake.IntakeRetract;
import frc.robot.commands.Intake.IntakeRetractHook;
import frc.robot.commands.Vision.VisionSetDriverMode;
import frc.robot.commands.Vision.VisionSetVisionMode;
import frcteam3255.robotbase.Preferences.SN_Preferences;

/**
 * Subsystem containing board telemetry methoods
 */
public class Telemetry extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	/**
	 * Put command buttons on the board
	 */
	public Telemetry() {
		SmartDashboard.putData("Reset Drive Encoder", new DriveResetEncoder());
		SmartDashboard.putData("Reset Yaw", new DriveResetYaw());
		SmartDashboard.putData("Set Driver Mode", new VisionSetDriverMode());
		SmartDashboard.putData("Set Vision Mode", new VisionSetVisionMode());
		SmartDashboard.putData("Cascade Lock Dogtooth", new CascadeLockDogtooth());
		SmartDashboard.putData("Cascade Unlock Dogtooth", new CascadeUnlockDogtooth());
		SmartDashboard.putData("Reset Cascade", new CascadeResetEncoder());
		SmartDashboard.putData("Shift Cascade", new CascadeShiftTo());
		SmartDashboard.putData("Shift Climb", new ClimbShiftTo());
		SmartDashboard.putData("Drive To Wall", new DriveToWall());
		SmartDashboard.putData("Deploy Fangs", new CascadeDeployClimb());
		SmartDashboard.putData("Retract Fangs", new CascadeRetractClimb());
		SmartDashboard.putData("Deploy Hook", new IntakeDeployHook());
		SmartDashboard.putData("Retract Hook", new IntakeRetractHook());
		SmartDashboard.putData("Intake Deploy", new IntakeDeploy());
		SmartDashboard.putData("Intake Retract", new IntakeRetract());

	}

	/**
	 * When this methood is in a periodic methood in Robot.java, these values are
	 * updated
	 */
	public void update() {
		SmartDashboard.putBoolean("Is Debug", !SN_Preferences.isUsingDefaults());
		SmartDashboard.putNumber("Drivetrain Encoder Count", Robot.m_drivetrain.getEncoderCount());
		SmartDashboard.putNumber("Drivetrain Encoder Distance", Robot.m_drivetrain.getEncoderDistance());
		SmartDashboard.putNumber("Vision Distance From Target", Robot.m_vision.getDistance());
		SmartDashboard.putNumber("Vision Rotation", Robot.m_vision.getRotation());
		SmartDashboard.putNumber("Vision Horizontal Offset", Robot.m_vision.getHorizontalOffset());
		SmartDashboard.putNumber("Vision Width", Robot.m_vision.getWidth());
		SmartDashboard.putNumber("Yaw", Robot.m_navigation.getYaw());
		SmartDashboard.putBoolean("Target Found", Robot.m_vision.targetFound());
		SmartDashboard.putNumber("Cascade Lift Count", Robot.m_cascade.getLiftEncoderCount());
		SmartDashboard.putNumber("Cascade Lift Distance", Robot.m_cascade.getLiftEncoderDistance());
		SmartDashboard.putBoolean("Cascade Top Switch", Robot.m_cascade.isTopSwitchClosed());
		SmartDashboard.putBoolean("Cascade Bottom Switch", Robot.m_cascade.isBottomSwitchClosed());
		SmartDashboard.putNumber("Acceleration X", Robot.m_navigation.getAccelerationX());
		SmartDashboard.putNumber("Acceleration Y", Robot.m_navigation.getAccelerationY());
		SmartDashboard.putNumber("Acceleration Z", Robot.m_navigation.getAccelerationZ());
		SmartDashboard.putBoolean("Is Calibrating", Robot.m_navigation.isCalibrating());

		SmartDashboard.putBoolean("Cargo Collected", Robot.m_intake.isCargoCollected());
		SmartDashboard.putBoolean("Hatch Collected", Robot.m_intake.isHatchCollected());
		SmartDashboard.putBoolean("Is Shifted To Cascade", Robot.m_cascade.isShiftedCascade());
		SmartDashboard.putBoolean("Intake Retracted", Robot.m_intake.isIntakeRetract());

		SmartDashboard.putData("Running Commands", Scheduler.getInstance());
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
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
