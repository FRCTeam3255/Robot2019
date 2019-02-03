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

        // Intake Preferences
        public static final SN_DoublePreference INTAKE_CARGO_SPEED = new SN_DoublePreference("intakeCargoSpeed", 1.0);
        public static final SN_DoublePreference EJECT_CARGO_SPEED = new SN_DoublePreference("ejectCargoSpeed", -1.0);

        // Cascade Preferences
        public static final SN_DoublePreference CASCADE_PULSES_PER_FOOT = new SN_DoublePreference(
                        "cascadePulsesPerFoot", 510.0);
        public static final SN_DoublePreference CASCADE_CLIMB_SETPOINT = new SN_DoublePreference("cascadeClimb", 25.0);

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
}