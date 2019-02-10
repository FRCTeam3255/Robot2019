/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frc.robot.commands.Drive.DriveArcade;
import frcteam3255.robotbase.SN_Math;
import frcteam3255.robotbase.SN_TalonSRX;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

/**
 * Subsytem containing the drivetrain devices and methoods
 */

public class Drivetrain extends Subsystem {
	private static SN_DoublePreference minCascadeHeight = new SN_DoublePreference("minCascadeHeight", 0.0);
	private static SN_DoublePreference maxCascadeHeight = new SN_DoublePreference("maxCascadeHeight", 100.0);
	private static SN_DoublePreference factorAtMinCascade = new SN_DoublePreference("factorAtMinCascade", 1.0);
	private static SN_DoublePreference factorAtMaxCascade = new SN_DoublePreference("factorAtMaxCascade", 0.2);

	private static SN_DoublePreference minStingerHeight = new SN_DoublePreference("minStingerHeight", 0.0);
	private static SN_DoublePreference maxStingerHeight = new SN_DoublePreference("maxStingerHeight", 30.0);
	private static SN_DoublePreference factorAtMinStinger = new SN_DoublePreference("factorAtMinStinger", 0.5);
	private static SN_DoublePreference factorAtMaxStinger = new SN_DoublePreference("factorAtMaxStinger", 0.2);

	// Talons
	private SpeedControllerGroup leftTalons = null;
	private SN_TalonSRX leftFrontTalon = null;
	private SN_TalonSRX leftMidTalon = null;
	private SN_TalonSRX leftBackTalon = null;

	private SpeedControllerGroup rightTalons = null;
	private SN_TalonSRX rightFrontTalon = null;
	private SN_TalonSRX rightMidTalon = null;
	private SN_TalonSRX rightBackTalon = null;

	private SN_TalonSRX centerBackTalon = null;

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
		// leftTalons = new SpeedControllerGroup(leftFrontTalon, leftBackTalon);
		leftTalons = new SpeedControllerGroup(leftFrontTalon, leftMidTalon, leftBackTalon);

		rightFrontTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_FRONT_TALON);
		rightMidTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_MID_TALON);
		rightBackTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_BACK_TALON);
		// rightTalons = new SpeedControllerGroup(rightFrontTalon, rightBackTalon);
		rightTalons = new SpeedControllerGroup(rightFrontTalon, rightMidTalon, rightBackTalon);

		centerBackTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_CENTER_BACK_TALON);

		// Encoders
		encoder = new Encoder(RobotMap.DRIVETRAIN_ENCODER_A, RobotMap.DRIVETRAIN_ENCODER_B);

		differentialDrive = new DifferentialDrive(leftTalons, rightTalons);
		differentialDrive.setSafetyEnabled(false);
	}

	/**
	 * acradeDrive but squared inputs defaults to false
	 * 
	 * @param moveSpeed
	 * @param rotateSpeed
	 */
	public void arcadeDrive(double moveSpeed, double rotateSpeed) {
		arcadeDrive(moveSpeed, rotateSpeed, false);
	}

	/**
	 * Drives the robot using a foward/backward (move) speed and a left right
	 * (rotate) speed
	 */
	public void arcadeDrive(double moveSpeed, double rotateSpeed, boolean squaredInputs) {
		double cascadeSpeedFactor = 1.0;

		double cascadeHeight = Math.abs(Robot.m_cascade.getLiftEncoderDistance());
		if (Robot.m_cascade.isShiftedCascade()) {
			cascadeSpeedFactor = SN_Math.interpolate(cascadeHeight, minCascadeHeight.getValue(),
					maxCascadeHeight.getValue(), factorAtMinCascade.getValue(), factorAtMaxCascade.getValue());
		} else {
			cascadeSpeedFactor = SN_Math.interpolate(cascadeHeight, minStingerHeight.getValue(),
					maxStingerHeight.getValue(), factorAtMinStinger.getValue(), factorAtMaxStinger.getValue());
		}

		// double cascadeSpeedFactor = ((-0.01 *
		// Math.abs(Robot.m_cascade.getLiftEncoderDistance())) + 1);

		moveSpeed = moveSpeed * cascadeSpeedFactor;
		rotateSpeed = rotateSpeed * cascadeSpeedFactor;

		differentialDrive.arcadeDrive(moveSpeed, rotateSpeed, squaredInputs);
	}

	/**
	 * @return Default scaled encoder count
	 */
	public double getEncoderCount() {
		return -encoder.get();
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
	 * Set the speed for the center back wheel
	 * 
	 * @param speed
	 */
	public void setBackSpeed(double speed) {
		centerBackTalon.set(speed);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveArcade());
	}
}