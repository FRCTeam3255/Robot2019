/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frcteam3255.robotbase.SN_DoublePreference;
import frcteam3255.robotbase.SN_IntPreference;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotPreferences {
  // Drivetrain preferences
  public static final SN_IntPreference DRIVETRAIN_PULSES_PER_FOOT = new SN_IntPreference("driveTrainPulsesPerFoot", 5);

  //Drive PID
  public static final SN_DoublePreference DRIVETRAIN_P = new SN_DoublePreference("driveP", 1.0);
  public static final SN_DoublePreference DRIVETRAIN_I = new SN_DoublePreference("driveI", 0.0);
  public static final SN_DoublePreference DRIVETRAIN_D = new SN_DoublePreference("driveD", 0.0);

  public static final SN_DoublePreference DRIVETRAIN_MAX_CHANGE = new SN_DoublePreference("driveMaxChange", 1);
  public static final SN_DoublePreference DRIVETRAIN_MIN = new SN_DoublePreference("driveMin", 0);
  public static final SN_DoublePreference DRIVETRAIN_MAX = new SN_DoublePreference("driveMax", 1);

  public static final SN_IntPreference DRIVETRAIN_TARGET_COUNT = new SN_IntPreference("driveTargetCount", 5000);
  public static final SN_IntPreference DRIVETRAIN_TARGET_TOLERANCE = new SN_IntPreference("driveTargetTolerance", 0);

  public static final SN_IntPreference DRIVETRAIN_TIMEOUT = new SN_IntPreference("driveTimeout", 10);

  //Yaw PID
  public static final SN_DoublePreference YAW_P = new SN_DoublePreference("yawP", 1.0);
  public static final SN_DoublePreference YAW_I = new SN_DoublePreference("yawI", 0.0);
  public static final SN_DoublePreference YAW_D = new SN_DoublePreference("yawD", 0.0);

  public static final SN_DoublePreference YAW_MAX_CHANGE = new SN_DoublePreference("yawMaxChange", 1);
  public static final SN_DoublePreference YAW_MIN = new SN_DoublePreference("yawMin", 0);
  public static final SN_DoublePreference YAW_MAX = new SN_DoublePreference("yawMax", 1);
  
  public static final SN_DoublePreference YAW_TARGET_TOLERANCE = new SN_DoublePreference("yawTargetTolerance", 0);
  public static final SN_IntPreference YAW_TARGET_COUNT = new SN_IntPreference("yawTargetCount", 5000);
}