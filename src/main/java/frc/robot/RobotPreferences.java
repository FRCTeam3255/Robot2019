/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frcteam3255.robotbase.Preferences.SN_DoublePreference;
import frcteam3255.robotbase.Preferences.SN_IntPreference;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotPreferences {

	public static final SN_DoublePreference p = new SN_DoublePreference("cP", .7);
	public static final SN_DoublePreference i = new SN_DoublePreference("cI", 0.0003);
	public static final SN_DoublePreference d = new SN_DoublePreference("cD", 0);
	public static final SN_DoublePreference f = new SN_DoublePreference("cF", 0);
	public static final SN_DoublePreference setpoint = new SN_DoublePreference("cSetpoint", 2367);
	public static final SN_IntPreference iz = new SN_IntPreference("Izone", 0);
	public static final SN_IntPreference tol = new SN_IntPreference("Tolerance", 1);

	public static final SN_IntPreference counts = new SN_IntPreference("Counts", 10000);
	public static final SN_IntPreference feet = new SN_IntPreference("5ft", 4950);

	public static final SN_IntPreference velocity = new SN_IntPreference("cVelocity", 10000);
	public static final SN_IntPreference acceleration = new SN_IntPreference("cAcceleration", 10000);

	public static final SN_IntPreference forwardSoftLimit = new SN_IntPreference("forSoftLimit", 1000);
	public static final SN_IntPreference reverseSoftLimit = new SN_IntPreference("revSoftLimit", 0);

	// end

	// cascade preferences (sourced from old CascadePID subsystem)

	public static final SN_DoublePreference CASCADE_TOL = new SN_DoublePreference("cascadeTol", 1.0);
	public static final SN_IntPreference CASCADE_TARGET_COUNT = new SN_IntPreference("cascadeTargetCount", 1);
	public static final SN_DoublePreference CASCADE_MINOUTDOWN = new SN_DoublePreference("cascadeMinSpeedDown", 0.0);
	public static final SN_DoublePreference CASCADE_MINOUTUP = new SN_DoublePreference("cascadeMinSpeedUp", 0.33);
	public static final SN_DoublePreference CASCADE_MAXOUTDOWN = new SN_DoublePreference("cascadeMaxSpeedDown", 0.1);
	public static final SN_DoublePreference CASCADE_MAXOUTUP = new SN_DoublePreference("cascadeMaxSpeedUp", 0.6);
	public static final SN_DoublePreference CASCADE_MAXCHANGE = new SN_DoublePreference("cascadeMaxChange", 1.0);

	// Drivetrain preferences
	// CompBot: 29 pulses per foot?
	public static final SN_DoublePreference DRIVETRAIN_PULSES_PER_FOOT = new SN_DoublePreference(
			"drivetrainPulsesPerFoot", 400.0);
	public static final SN_DoublePreference DRIVETRAIN_CLIMB_SETPOINT = new SN_DoublePreference("drivetrainClimb",
			25.0);
	public static final SN_DoublePreference DECELERATION_SPEED = new SN_DoublePreference("decelerationSpeed", 0.5);
	public static final SN_DoublePreference SLOW_SPEED_MOVE_FACTOR = new SN_DoublePreference("slowSpeedMoveFactor",
			0.5);
	public static final SN_DoublePreference SLOW_SPEED_ROTATE_FACTOR = new SN_DoublePreference("slowSpeedRotateFactor",
			0.71);
	public static final SN_DoublePreference HIGH_SPEED_MOVE_FACTOR = new SN_DoublePreference("highSpeedMoveFactor",
			1.0);
	public static final SN_DoublePreference HIGH_SPEED_ROTATE_FACTOR = new SN_DoublePreference("highSpeedRotateFactor",
			0.8);

	// Intake Preferences
	public static final SN_DoublePreference CARGO_COLLECT_SPEED = new SN_DoublePreference("cargoCollectSpeed", 1.0);
	public static final SN_DoublePreference CARGO_SHOOT_SPEED = new SN_DoublePreference("cargoShootSpeed", -1.0);

	// Cascade Preferences
	// CompBot: 510 pulses per foot
	public static final SN_DoublePreference CASCADE_PULSES_PER_FOOT = new SN_DoublePreference("cascadePulsesPerFoot",
			110.0);
	public static final SN_DoublePreference HATCH_POSITION_1 = new SN_DoublePreference("hatchPos1", 0.0);
	public static final SN_DoublePreference HATCH_POSITION_2 = new SN_DoublePreference("hatchPos2", 31.0);
	public static final SN_DoublePreference HATCH_POSITION_3 = new SN_DoublePreference("hatchPos3", 60.0);
	public static final SN_DoublePreference HATCH_POSITION_LOADED = new SN_DoublePreference("hatchPosLoaded", 10.0);
	public static final SN_DoublePreference CARGO_POSITION_1 = new SN_DoublePreference("cargoPos1", 22.0);
	public static final SN_DoublePreference CARGO_POSITION_2 = new SN_DoublePreference("cargoPos2", 52.0);
	public static final SN_DoublePreference CARGO_POSITION_3 = new SN_DoublePreference("cargoPos3", 73.0);
	public static final SN_DoublePreference CARGO_POSITION_SHIP = new SN_DoublePreference("cargoPosShip", 37.0);
	public static final SN_DoublePreference CASCADE_BOTTOM = new SN_DoublePreference("cascadeBottom", 0.0);
	public static final SN_DoublePreference CASCADE_FEEDER = new SN_DoublePreference("cascadeFeeder", 1.0);
	public static final SN_DoublePreference CASCADE_BOTTOM_SPEED = new SN_DoublePreference("cascadeBottomSpeed", -0.2);
	public static final SN_DoublePreference CASCADE_RELEASE_DISTANCE = new SN_DoublePreference("cascadeReleaseDist",
			0.5);
	public static final SN_DoublePreference CASCADE_RELEASE_SPEED = new SN_DoublePreference("cascadeReleaseSpeed",
			0.33);
	public static final SN_DoublePreference CASCADE_UNWEIGHT_SPEED = new SN_DoublePreference("cascadeUnweightSpeed",
			0.4);
	public static final SN_DoublePreference CASCADE_UNWEIGHT_HEIGHT = new SN_DoublePreference("cascadeUnweightHeight",
			100);

	// Climber
	public static final SN_DoublePreference CLIMBER_LIFT_SPEED = new SN_DoublePreference("climbLiftSpeed", 0.5);
	public static final SN_DoublePreference CLIMB_LIFT_DELAY = new SN_DoublePreference("climbLiftDelay", 0.5);

	// Drive PID
	public static final SN_DoublePreference DRIVETRAIN_P = new SN_DoublePreference("driveP", 10.0);
	public static final SN_DoublePreference DRIVETRAIN_I = new SN_DoublePreference("driveI", 0.0);
	public static final SN_DoublePreference DRIVETRAIN_D = new SN_DoublePreference("driveD", 0.0);
	public static final SN_DoublePreference DRIVETRAIN_F = new SN_DoublePreference("driveF", 0.0);
	public static final SN_IntPreference DRIVETRAIN_IZONE = new SN_IntPreference("driveIZone", 0);
	public static final SN_IntPreference DRIVETRAIN_TOLERANCE = new SN_IntPreference("driveTol", 0);

	// Yaw PID
	public static final SN_DoublePreference YAW_P = new SN_DoublePreference("yawP", 0.02);

	// Vision Distance PID
	public static final SN_DoublePreference VISION_DISTANCE_P = new SN_DoublePreference("visionDistanceP", 0.02);
	public static final SN_DoublePreference VISION_DISTANCE_I = new SN_DoublePreference("visionDistanceI", 0.0);
	public static final SN_DoublePreference VISION_DISTANCE_D = new SN_DoublePreference("visionDistanceD", 0.0);

	// Vision Rotate PID
	public static final SN_DoublePreference VISION_ROTATE_P = new SN_DoublePreference("visionRotateP", 0.02);
	public static final SN_DoublePreference VISION_ROTATE_I = new SN_DoublePreference("visionRotateI", 0.0);
	public static final SN_DoublePreference VISION_ROTATE_D = new SN_DoublePreference("visionRotateD", 0.0);
	public static final SN_DoublePreference VISION_ZERO_SETPOINT = new SN_DoublePreference("visionZeroSetpoint", 0.0);

	// Autonomous
	public static final SN_DoublePreference AUTO_DELAY = new SN_DoublePreference("autoDelay", 0.25);
	public static final SN_DoublePreference AUTO_DISTANCE_ON_TARGET = new SN_DoublePreference("autoDistanceVision",
			10.0);
	public static final SN_DoublePreference AUTO_ROTATE_ON_TARGET = new SN_DoublePreference("autoRotateVision", 0.0);
	public static final SN_DoublePreference DRIVE_ROTATE_TEST_DISTANCE = new SN_DoublePreference(
			"dirveRotateTestDistance", 25);
	public static final SN_DoublePreference DRIVE_ROTATE_TEST_ANGLE = new SN_DoublePreference("dirveRotateTestRotate",
			0);
}