/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frc.robot.commands.Drive.DriveArcade;
import frcteam3255.robotbase.SN_TalonSRX;

/**
 * Subsytem containing the drivetrain devices and methoods
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	// Talons
	private SpeedControllerGroup leftTalons = null;
	private SN_TalonSRX leftFrontTalon = null;
	private SN_TalonSRX leftMidTalon = null;
	private SN_TalonSRX leftBackTalon = null;

	private SpeedControllerGroup rightTalons = null;
	private SN_TalonSRX rightFrontTalon = null;
	private SN_TalonSRX rightMidTalon = null;
	private SN_TalonSRX rightBackTalon = null;

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
		leftTalons = new SpeedControllerGroup(leftFrontTalon, leftBackTalon);
		leftTalons = new SpeedControllerGroup(leftFrontTalon, leftMidTalon, leftBackTalon);

		rightFrontTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_FRONT_TALON);
		rightMidTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_MID_TALON);
		rightBackTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_BACK_TALON);
		rightTalons = new SpeedControllerGroup(rightFrontTalon, rightBackTalon);
		rightTalons = new SpeedControllerGroup(rightFrontTalon, rightMidTalon, rightBackTalon);
		rightTalons = new SpeedControllerGroup(rightFrontTalon, rightMidTalon, rightBackTalon);

		// Encoders
		encoder = new Encoder(RobotMap.DRIVETRAIN_ENCODER_A, RobotMap.DRIVETRAIN_ENCODER_B);

		differentialDrive = new DifferentialDrive(leftTalons, rightTalons);
		differentialDrive.setSafetyEnabled(false);
	}

	/**
	 * Drives the robot using a foward/backward (move) speed and a left right
	 * (rotate) speed
	 */
	public void arcadeDrive(double moveSpeed, double rotateSpeed, boolean squaredInputs) {
		differentialDrive.arcadeDrive(moveSpeed, rotateSpeed, true);
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

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveArcade());
	}
}