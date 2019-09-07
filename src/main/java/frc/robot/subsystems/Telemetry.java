/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.AutoPreferences;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frc.robot.commands.Cascade.CascadeLockDogtooth;
import frc.robot.commands.Cascade.CascadePositionGroup;
import frc.robot.commands.Cascade.CascadeResetEncoder;
import frc.robot.commands.Cascade.CascadeShiftTo;
import frc.robot.commands.Cascade.CascadeUnlockDogtooth;
import frc.robot.commands.Climber.ClimbDeploy;
import frc.robot.commands.Climber.ClimbShiftTo;
import frc.robot.commands.Drive.DriveDistance;
import frc.robot.commands.Drive.DriveResetEncoder;
import frc.robot.commands.Drive.DriveResetYaw;
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

	private ShuffleboardTab commandTab = Shuffleboard.getTab("Commands");
	private ShuffleboardTab dataTab = Shuffleboard.getTab("Data");
	private ShuffleboardLayout cascadeCommands = Shuffleboard.getTab("Commands")
			.getLayout("Cascade Commands", BuiltInLayouts.kList).withSize(3, 5);
	private ShuffleboardLayout dtCommands = Shuffleboard.getTab("Commands")
			.getLayout("Drivetrain Commands", BuiltInLayouts.kList).withSize(3, 5);
	private ShuffleboardLayout intakeCommands = Shuffleboard.getTab("Commands")
			.getLayout("Intake Commands", BuiltInLayouts.kList).withSize(3, 5);
	private ShuffleboardLayout climbCommands = Shuffleboard.getTab("Commands")
			.getLayout("Climb Commands", BuiltInLayouts.kList).withSize(3, 5);
	private ShuffleboardLayout navCommands = Shuffleboard.getTab("Commands")
			.getLayout("Navigation Commands", BuiltInLayouts.kList).withSize(3, 5);

	// Cascade Telemetry
	NetworkTableEntry cascadeLiftCount = dataTab.add("Cascade Lift Count", Robot.m_cascade.getLiftEncoderCount())
			.getEntry();
	NetworkTableEntry cascadeLiftDistance = dataTab
			.add("Cascade Lift Distance", Robot.m_cascade.getLiftEncoderDistance()).getEntry();
	NetworkTableEntry cascadeTopSwitch = dataTab.add("Cascade Top Switch", Robot.m_cascade.isTopSwitchClosed())
			.getEntry();
	NetworkTableEntry cascadeBottomSwitch = dataTab.add("Cascade Bottom Switch", Robot.m_cascade.isBottomSwitchClosed())
			.getEntry();
	NetworkTableEntry cascadeMode = dataTab.add("Cascade Mode", Robot.m_cascade.isShiftedCascade()).getEntry();
	NetworkTableEntry cascadeLiftErr = dataTab.add("Cascade Lift Error", Robot.m_cascade.talonPidError()).getEntry();
	NetworkTableEntry cascadeLiftSpeed = dataTab.add("Lift Speed", Robot.m_cascade.getLiftSpeed()).getEntry();

	// Drivetrain Telemetry
	NetworkTableEntry dtEncoderCount = dataTab.add("Drivetrain Encoder Count", Robot.m_drivetrain.getEncoderCount())
			.getEntry();
	NetworkTableEntry dtError = dataTab.add("Drivetrain Error", Robot.m_drivetrain.pidError()).getEntry();
	NetworkTableEntry dtSensorPhase = dataTab.add("Is Sensor Out of Phase", Robot.m_drivetrain.isOutOfPhase())
			.getEntry();
	// Intake Telemetry
	NetworkTableEntry intakeCargoRetracted = dataTab
			.add("Cargo Intake Retracted", Robot.m_intake.isCargoIntakeRetracted()).getEntry();
	NetworkTableEntry intakeLinkageRetracted = dataTab.add("Linkage Retracted", Robot.m_intake.isLinkageRetracted())
			.getEntry();
	NetworkTableEntry intakeHatchCollected = dataTab.add("Hatch Collected", Robot.m_intake.isHatchCollected())
			.getEntry();
	NetworkTableEntry intakeCargoCollected = dataTab.add("Cargo Collected", Robot.m_intake.isCargoCollected())
			.getEntry();

	// Lighting Telemetry

	// Vision Telemetry
	NetworkTableEntry visionTargetFound = dataTab.add("Target Found", Robot.m_vision.targetFound()).getEntry();
	NetworkTableEntry visionDistance = dataTab.add("Vision Distance From Target", Robot.m_vision.getDistance())
			.getEntry();
	NetworkTableEntry visionRotation = dataTab.add("Vision Rotation", Robot.m_vision.getRotation()).getEntry();
	NetworkTableEntry visionOffset = dataTab.add("Vision Horizontal Offset", Robot.m_vision.getHorizontalOffset())
			.getEntry();
	NetworkTableEntry visionWidth = dataTab.add("Vision Width", Robot.m_vision.getWidth()).getEntry();

	// Other Telemetry
	NetworkTableEntry autoGetSide = dataTab.add("get Side", AutoPreferences.getSide()).getEntry();
	NetworkTableEntry autoGetPos = dataTab.add("get position", AutoPreferences.getPosition()).getEntry();
	NetworkTableEntry cmdStatus = dataTab.add("Command Status", "none").getEntry();

	// PowerDistributionPanel pdp;

	/**
	 * Put command buttons on the board
	 */
	public Telemetry() {
		// pdp = new PowerDistributionPanel();
		// Cascade Commands
		cascadeCommands.add("Lock Dogtooth", new CascadeLockDogtooth());
		cascadeCommands.add("Unlock Dogtooth", new CascadeUnlockDogtooth());
		cascadeCommands.add("Shift Cascade", new CascadeShiftTo());
		cascadeCommands.add("Shift Climb", new ClimbShiftTo());
		cascadeCommands.add("Reset Cascade Encoder", new CascadeResetEncoder());

		// Drivetrain Commands
		dtCommands.add("Reset Drive Encoder", new DriveResetEncoder());
		dtCommands.add("Drive10kcounts", new DriveDistance(RobotPreferences.counts, "10000 Counts"));
		dtCommands.add("Drive 5 ft", new DriveDistance(RobotPreferences.feet, "5ft"));

		// Intake Commands
		intakeCommands.add("Deploy Cargo Intake", new IntakeCargoDeploy());
		intakeCommands.add("Retract Cargo Intake", new IntakeCargoRetract());
		intakeCommands.add("Deploy Linkage", new IntakeLinkageDeploy());
		intakeCommands.add("Retract Linkage", new IntakeLinkageRetract());

		intakeCommands.add("Deploy Finger", new IntakeFingerDeploy());
		intakeCommands.add("Retract Finger", new IntakeFingerRetract());
		intakeCommands.add("Eject", new IntakeHatchEject());

		// Climb Commands
		climbCommands.add("Climb Deploy", new ClimbDeploy());

		// Lighting Commands

		// Navigation Commands
		navCommands.add("Reset Yaw", new DriveResetYaw());

	}

	/**
	 * When this methood is in a periodic methood in Robot.java, these values are
	 * updated
	 */
	public void update() {

		// Cascade Telemetry
		cascadeLiftCount.setDouble(Robot.m_cascade.getLiftEncoderCount());
		cascadeLiftDistance.setDouble(Robot.m_cascade.getLiftEncoderDistance());
		cascadeTopSwitch.setBoolean(Robot.m_cascade.isTopSwitchClosed());
		cascadeBottomSwitch.setBoolean(Robot.m_cascade.isBottomSwitchClosed());
		cascadeMode.setBoolean(Robot.m_cascade.isShiftedCascade());
		cascadeLiftErr.setDouble(Robot.m_cascade.talonPidError());
		cascadeLiftSpeed.setDouble(Robot.m_cascade.getLiftSpeed());

		// Drivetrain Telemetry
		dtEncoderCount.setDouble(Robot.m_drivetrain.getEncoderCount());
		dtError.setDouble(Robot.m_drivetrain.pidError());
		dtSensorPhase.setBoolean(Robot.m_drivetrain.isOutOfPhase());
		// Intake Telemetry
		intakeCargoRetracted.setBoolean(Robot.m_intake.isCargoIntakeRetracted());
		intakeLinkageRetracted.setBoolean(Robot.m_intake.isLinkageRetracted());
		intakeHatchCollected.setBoolean(Robot.m_intake.isHatchCollected());
		intakeCargoCollected.setBoolean(Robot.m_intake.isCargoCollected());

		// Lighting Telemetry

		// Vision Telemetry
		visionTargetFound.setBoolean(Robot.m_vision.targetFound());
		visionDistance.setDouble(Robot.m_vision.getDistance());
		visionRotation.setDouble(Robot.m_vision.getRotation());
		visionOffset.setDouble(Robot.m_vision.getHorizontalOffset());
		visionWidth.setDouble(Robot.m_vision.getWidth());

		// Other Telemetry
		autoGetSide.setString(AutoPreferences.getSide());
		autoGetPos.setString(AutoPreferences.getPosition());

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
		cmdStatus.setString(commandState);
	}
}
