/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frc.robot.commands.Drive.DriveArcade;
import frcteam3255.robotbase.SN_TalonSRX;
import frcteam3255.robotbase.Preferences.SN_BooleanPreference;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;
import frcteam3255.robotbase.Preferences.SN_IntPreference;

/**
 * Subsytem containing the drivetrain devices and methoods
 */
public class Drivetrain extends Subsystem {
	// private static SN_DoublePreference MIN_CASCADE_HEIGHT = new
	// SN_DoublePreference("minCascadeHeight", 0.0);
	// private static SN_DoublePreference MAX_CASCADE_HEIGHT = new
	// SN_DoublePreference("maxCascadeHeight", 100.0);
	// private static SN_DoublePreference FACTOR_AT_MIN_CASCADE = new
	// SN_DoublePreference("factorAtMinCascade", 1.0);
	// private static SN_DoublePreference FACTOR_AT_MAX_CASCADE = new
	// SN_DoublePreference("factorAtMaxCascade", 0.8);

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
	private SN_TalonSRX leftFrontTalon = null;
	private SN_TalonSRX leftMidTalon = null;
	private SN_TalonSRX leftBackTalon = null;

	private SN_TalonSRX rightFrontTalon = null;
	private SN_TalonSRX rightMidTalon = null;
	private SN_TalonSRX rightBackTalon = null;

	private SN_TalonSRX climbDriveTalon = null;

	private Faults faults = new Faults();
	TalonSRXConfiguration currentLimitConfig;

	private DifferentialDrive differentialDrive = null;

	/**
	 * Creates the devices used in the drivetrain
	 */
	public Drivetrain() {

		// Initializes talons
		leftFrontTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_LEFT_FRONT_TALON);
		leftMidTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_LEFT_MID_TALON, leftFrontTalon, false);
		leftBackTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_LEFT_BACK_TALON, leftFrontTalon, false);

		rightFrontTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_FRONT_TALON);
		rightMidTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_MID_TALON, rightFrontTalon, false);
		rightBackTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_BACK_TALON, rightFrontTalon, false);

		climbDriveTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_CLIMB_TALON);

		// Configure Position PID loop
		leftFrontTalon.configurePositionPid(FeedbackDevice.QuadEncoder, RobotPreferences.DRIVETRAIN_P,
				RobotPreferences.DRIVETRAIN_I, RobotPreferences.DRIVETRAIN_D, RobotPreferences.DRIVETRAIN_F,
				RobotPreferences.DRIVETRAIN_IZONE, RobotPreferences.DRIVETRAIN_TOLERANCE, true);

		// Current Limiting Assignment
		leftFrontTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		leftMidTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		leftBackTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);

		rightFrontTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		rightMidTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		rightBackTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);

		// Differential Drive initialization
		differentialDrive = new DifferentialDrive(leftFrontTalon, rightFrontTalon);
		differentialDrive.setSafetyEnabled(false);
	}

	/**
	 * Drives the robot using a foward/backward (move) speed and a left right
	 * (rotate) speed.
	 */
	public void arcadeDrive(double moveSpeed, double rotateSpeed, boolean squaredInputs) {
		differentialDrive.arcadeDrive(moveSpeed, rotateSpeed, squaredInputs);
	}

	public double rotationPid(double yaw, double goal, double p) {
		double output = 0;
		output = ((goal - yaw) * p);

		return output;
	}

	/**
	 * @return Default scaled encoder count
	 */
	public double getEncoderCount() {
		return leftFrontTalon.getSelectedSensorPosition();
	}

	/**
	 * Resets the encoder to zero
	 */
	public void resetEncoderCount() {
		leftFrontTalon.resetEncoder();
	}

	// Encoder distance scaled to inches. Commented out for testing
	// /**
	// * @return Encoder distance in inches
	// */
	// public double getEncoderDistance() {
	// return (getEncoderCount() /
	// RobotPreferences.DRIVETRAIN_PULSES_PER_FOOT.getValue()) * 12;
	// }

	// Method to begin PID loop mode on Talons
	public void pid(double setpoint) {
		resetEncoderCount();
		leftFrontTalon.set(ControlMode.Position, setpoint);
		leftFrontTalon.setInverted(false);
		leftMidTalon.follow(leftFrontTalon);
		leftBackTalon.follow(leftFrontTalon);
		rightFrontTalon.follow(leftFrontTalon);
		rightFrontTalon.setInverted(InvertType.OpposeMaster);
		rightMidTalon.follow(leftFrontTalon);
		rightMidTalon.setInverted(InvertType.OpposeMaster);
		rightBackTalon.follow(leftFrontTalon);
		rightBackTalon.setInverted(InvertType.OpposeMaster);

	}

	public void talonReset() {
		leftFrontTalon.set(0);
		rightFrontTalon.setInverted(false);
		rightMidTalon.follow(rightFrontTalon);
		rightMidTalon.setInverted(false);
		rightBackTalon.follow(rightFrontTalon);
		rightBackTalon.setInverted(false);

	}

	public boolean isOutOfPhase() {
		leftFrontTalon.getFaults(faults);
		return faults.SensorOutOfPhase;
	}

	public int pidError() {
		return leftFrontTalon.getClosedLoopError();
	}

	public boolean pidEnd() {
		return false;
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