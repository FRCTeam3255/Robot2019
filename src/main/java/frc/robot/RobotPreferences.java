/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frcteam3255.robotbase.Preferences.SN_DoublePreference;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotPreferences {
	// Drivetrain preferences
	public static final SN_DoublePreference DRIVETRAIN_PULSES_PER_FOOT = new SN_DoublePreference(
			"drivetrainPulsesPerFoot", 13.3);
	public static final SN_DoublePreference DRIVETRAIN_CLIMB_SETPOINT = new SN_DoublePreference("drivetrainClimb",
			25.0);
	public static final SN_DoublePreference DECELERATION_SPEED = new SN_DoublePreference("decelerationSpeed", 0.5);
	public static final SN_DoublePreference slowSpeedMoveFactor = new SN_DoublePreference("slowSpeedMoveFactor", 0.5);
	public static final SN_DoublePreference slowSpeedRotateFactor = new SN_DoublePreference("slowSpeedRotateFactor",
			0.3);
	public static final SN_DoublePreference highSpeedMoveFactor = new SN_DoublePreference("highSpeedMoveFactor", 1.0);
	public static final SN_DoublePreference highSpeedRotateFactor = new SN_DoublePreference("highSpeedRotateFactor",
			1.0);

	// Intake Preferences
	public static final SN_DoublePreference CARGO_COLLECT_SPEED = new SN_DoublePreference("cargoCollectSpeed", 1.0);
	public static final SN_DoublePreference CARGO_SHOOT_SPEED = new SN_DoublePreference("cargoShootSpeed", -1.0);

	// Cascade Preferences
	public static final SN_DoublePreference CASCADE_PULSES_PER_FOOT = new SN_DoublePreference("cascadePulsesPerFoot",
			510.0);
	public static final SN_DoublePreference HATCH_POSITION_1 = new SN_DoublePreference("hatchPos1", 5.0);
	public static final SN_DoublePreference HATCH_POSITION_2 = new SN_DoublePreference("hatchPos2", 28.0);
	public static final SN_DoublePreference HATCH_POSITION_3 = new SN_DoublePreference("hatchPos3", 51.0);
	public static final SN_DoublePreference CARGO_POSITION_1 = new SN_DoublePreference("cargoPos1", 21.5);
	public static final SN_DoublePreference CARGO_POSITION_2 = new SN_DoublePreference("cargoPos2", 47.0);
	public static final SN_DoublePreference CARGO_POSITION_3 = new SN_DoublePreference("cargoPos3", 61.0);
	public static final SN_DoublePreference CASCADE_BOTTOM = new SN_DoublePreference("cascadeBottom", 0.0);
	public static final SN_DoublePreference CASCADE_BOTTOM_SPEED = new SN_DoublePreference("cascadeBottomSpeed", -0.2);
	public static final SN_DoublePreference CASCADE_UNWEIGHT_SPEED = new SN_DoublePreference("cascadeUnweightSpeed",
			0.5);
	public static final SN_DoublePreference CASCADE_UNWEIGHT_HEIGHT = new SN_DoublePreference("cascadeUnweightHeight",
			0.3);

	// Climber
	public static final SN_DoublePreference CLIMBER_LIFT_SPEED = new SN_DoublePreference("climbLiftSpeed", 0.5);
	public static final SN_DoublePreference CLIMB_LIFT_DELAY = new SN_DoublePreference("climbLiftDelay", 10.0);

	// Drive PID
	public static final SN_DoublePreference DRIVETRAIN_P = new SN_DoublePreference("driveP", 0.04);
	public static final SN_DoublePreference DRIVETRAIN_I = new SN_DoublePreference("driveI", 0.0);
	public static final SN_DoublePreference DRIVETRAIN_D = new SN_DoublePreference("driveD", 0.0);

	// Yaw PID
	public static final SN_DoublePreference YAW_P = new SN_DoublePreference("yawP", 0.02);
	public static final SN_DoublePreference YAW_I = new SN_DoublePreference("yawI", 0.0);
	public static final SN_DoublePreference YAW_D = new SN_DoublePreference("yawD", 0.0);

	// Vision Distance PID
	public static final SN_DoublePreference VISION_DISTANCE_P = new SN_DoublePreference("visionDistanceP", 0.08);
	public static final SN_DoublePreference VISION_DISTANCE_I = new SN_DoublePreference("visionDistanceI", 0.0);
	public static final SN_DoublePreference VISION_DISTANCE_D = new SN_DoublePreference("visionDistanceD", 0.0);
	// Vision Rotate PID
	public static final SN_DoublePreference VISION_ROTATE_P = new SN_DoublePreference("visionRotateP", 0.04);
	public static final SN_DoublePreference VISION_ROTATE_I = new SN_DoublePreference("visionRotateI", 0.0);
	public static final SN_DoublePreference VISION_ROTATE_D = new SN_DoublePreference("visionRotateD", 0.0);

	// testing
	public static final SN_DoublePreference TESTING_SETPOINT = new SN_DoublePreference("testing", 100.0);
}