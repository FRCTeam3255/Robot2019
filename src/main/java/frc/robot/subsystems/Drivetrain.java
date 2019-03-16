/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frc.robot.commands.Drive.DriveArcade;
import frcteam3255.robotbase.SN_Math;
import frcteam3255.robotbase.SN_TalonSRX;
import frcteam3255.robotbase.Preferences.SN_BooleanPreference;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;
import frcteam3255.robotbase.Preferences.SN_IntPreference;

/**
 * Subsytem containing the drivetrain devices and methoods
 */
public class Drivetrain extends Subsystem {
	private static SN_DoublePreference MIN_CASCADE_HEIGHT = new SN_DoublePreference("minCascadeHeight", 0.0);
	private static SN_DoublePreference MAX_CASCADE_HEIGHT = new SN_DoublePreference("maxCascadeHeight", 100.0);
	private static SN_DoublePreference FACTOR_AT_MIN_CASCADE = new SN_DoublePreference("factorAtMinCascade", 1.0);
	private static SN_DoublePreference FACTOR_AT_MAX_CASCADE = new SN_DoublePreference("factorAtMaxCascade", 0.2);

	private static SN_DoublePreference CLIMB_DRIVE_SPEED = new SN_DoublePreference("climbDriveSpeed", 1.0);
	private static SN_DoublePreference CLIMB_DEPLOY_SPEED = new SN_DoublePreference("climbDeploySpeed", -1.0);

	/** Current threshold to trigger current limit */
	private static final SN_IntPreference PEAK_AMPS = new SN_IntPreference("drivePeakAmps", 17);
	/**
	 * Duration (in miliseconds i.e. 5000ms = 5s) after current exceed Peak Current
	 * to trigger current limit
	 */
	private static final SN_IntPreference PEAK_TIME = new SN_IntPreference("drivePeakTimeMs", 5000);
	/** Current to mantain once current limit has been triggered */
	private static final SN_IntPreference LIMIT_AMPS = new SN_IntPreference("driveLimitAmps", 6);
	/** Set if current is limited */
	private static final SN_BooleanPreference ENABLE_CURRENT_LIMITING = new SN_BooleanPreference(
			"driveEnableCurrentLimit", false);

	// Talons
	private SpeedControllerGroup leftTalons = null;
	private SN_TalonSRX leftFrontTalon = null;
	private SN_TalonSRX leftMidTalon = null;
	private SN_TalonSRX leftBackTalon = null;

	private SpeedControllerGroup rightTalons = null;
	private SN_TalonSRX rightFrontTalon = null;
	private SN_TalonSRX rightMidTalon = null;
	private SN_TalonSRX rightBackTalon = null;

	private SN_TalonSRX climbDriveTalon = null;

	TalonSRXConfiguration currentLimitConfig;

	private DifferentialDrive differentialDrive = null;

	// Encoders
	private Encoder encoder = null;

	/**
	 * Creates the devices used in the drivetrain
	 */
	public Drivetrain() {
		// Talons
		leftFrontTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_LEFT_FRONT_TALON);
		leftMidTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_LEFT_MID_TALON);
		leftBackTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_LEFT_BACK_TALON);
		leftTalons = new SpeedControllerGroup(leftFrontTalon, leftMidTalon, leftBackTalon);

		rightFrontTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_FRONT_TALON);
		rightMidTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_MID_TALON);
		rightBackTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_BACK_TALON);
		rightTalons = new SpeedControllerGroup(rightFrontTalon, rightMidTalon, rightBackTalon);

		climbDriveTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_CLIMB_TALON);

		// Current Limiting Assignment
		leftFrontTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		leftMidTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		leftBackTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);

		rightFrontTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		rightMidTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		rightBackTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);

		// Encoders
		encoder = new Encoder(RobotMap.DRIVETRAIN_ENCODER_A, RobotMap.DRIVETRAIN_ENCODER_B);

		differentialDrive = new DifferentialDrive(leftTalons, rightTalons);
		differentialDrive.setSafetyEnabled(false);
	}

	/**
	 * Drives the robot using a foward/backward (move) speed and a left right
	 * (rotate) speed. Ramp the speed in cascade mode.
	 */
	public void arcadeDrive(double moveSpeed, double rotateSpeed) {
		double cascadeSpeedFactor = 1.0;

		if (Robot.m_cascade.isShiftedCascade()) {
			double cascadeHeight = Math.abs(Robot.m_cascade.getLiftEncoderDistance());
			cascadeSpeedFactor = SN_Math.interpolate(cascadeHeight, MIN_CASCADE_HEIGHT.getValue(),
					MAX_CASCADE_HEIGHT.getValue(), FACTOR_AT_MIN_CASCADE.getValue(), FACTOR_AT_MAX_CASCADE.getValue());
		}

		moveSpeed = moveSpeed * cascadeSpeedFactor;
		rotateSpeed = rotateSpeed * cascadeSpeedFactor;

		differentialDrive.arcadeDrive(moveSpeed, rotateSpeed, false);
	}

	/**
	 * @return Default scaled encoder count
	 */
	public double getEncoderCount() {
		return encoder.get();
	}

	/**
	 * Resets the encoder to zero
	 */
	public void resetEncoderCount() {
		encoder.reset();
	}

	/**
	 * @return Encoder distance in inches
	 */
	public double getEncoderDistance() {
		return (getEncoderCount() / RobotPreferences.DRIVETRAIN_PULSES_PER_FOOT.getValue()) * 12;
	}

	/**
	 * Turn on the climb drive wheel
	 */
	public void enableClimbDrive() {
		climbDriveTalon.set(CLIMB_DRIVE_SPEED.getValue());
	}

	/**
	 * Turn off the climb drive wheel
	 */
	public void disableClimbDrive() {
		climbDriveTalon.set(0.0);
	}

	/**
	 * Flips the climber mechanism out
	 */
	public void deployClimb() {
		climbDriveTalon.set(CLIMB_DEPLOY_SPEED.getValue());
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveArcade());
	}
}