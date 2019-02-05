/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// Talons
	public static final int DRIVETRAIN_LEFT_FRONT_TALON = 1;
	public static final int DRIVETRAIN_LEFT_MID_TALON = 2;
	public static final int DRIVETRAIN_LEFT_BACK_TALON = 3;
	public static final int DRIVETRAIN_RIGHT_FRONT_TALON = 4;
	public static final int DRIVETRAIN_RIGHT_MID_TALON = 5;
	public static final int DRIVETRAIN_RIGHT_BACK_TALON = 6;

	public static final int INTAKE_CARGO_TALON = 8;

	public static final int CASCADE_LEFT_FRONT_TALON = 9;
	public static final int CASCADE_LEFT_BACK_TALON = 10;
	public static final int CASCADE_RIGHT_BACK_TALON = 11;
	public static final int CASCADE_RIGHT_FRONT_TALON = 12;

	// Encoders
	public static final int DRIVETRAIN_ENCODER_B = 0;
	public static final int DRIVETRAIN_ENCODER_A = 1;

	public static final int CASCADE_LIFT_ENCODER_A = 2;
	public static final int CASCADE_LIFT_ENCODER_B = 3;

	// Digital Inputs
	public static final int INTAKE_HATCH_SWITCH = 4;
	public static final int INTAKE_CARGO_SWITCH = 5;

	public static final int CASCADE_TOP_SWITCH = 6;
	public static final int CASCADE_BOTTOM_SWITCH = 7;

	// PCMs
	public static final int INTAKE_PCM = 0;
	public static final int CASCADE_PCM = 1;

	// Solenoids
	// Intake PCM
	public static final int INTAKE_EJECT_SOLENOID_A = 0;
	public static final int INTAKE_EJECT_SOLENOID_B = 1;
	public static final int INTAKE_HATCH_DEPLOY_SOLENOID_A = 2;
	public static final int INTAKE_HATCH_DEPLOY_SOLENOID_B = 3;
	public static final int INTAKE_HATCH_INTAKE_SOLENOID_A = 4;
	public static final int INTAKE_HATCH_INTAKE_SOLENOID_B = 5;
	public static final int INTAKE_DEPLOY_SOLENOID_A = 6;
	public static final int INTAKE_DEPLOY_SOLENOID_B = 7;

	// Cascade PCM
	public static final int CASCADE_CLIMB_SOLENOID_A = 4;
	public static final int CASCADE_CLIMB_SOLENOID_B = 5;
	public static final int CASCADE_SHIFT_SOLENOID_A = 2;
	public static final int CASCADE_SHIFT_SOLENOID_B = 3;
	public static final int CASCADE_LOCK_SOLENOID_A = 0;
	public static final int CASCADE_LOCK_SOLENOID_B = 1;
}