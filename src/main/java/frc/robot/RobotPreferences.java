/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frcteam3255.robotbase.SN_Preference;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotPreferences {
  // Drivetrain preferences
  public static final SN_Preference DRIVETRAIN_PULSES_PER_FOOT = new SN_Preference("driveTrainPulsesPerFoot", 5);

  public static final SN_Preference DRIVETRAIN_P = new SN_Preference("driveP", 1);
  public static final SN_Preference DRIVETRAIN_I = new SN_Preference("driveI", 0);
  public static final SN_Preference DRIVETRAIN_D = new SN_Preference("driveD", 0);

  public static final SN_Preference DRIVETRAIN_MAX_CHANGE = new SN_Preference("driveMaxChange", 1);
  public static final SN_Preference DRIVETRAIN_MIN = new SN_Preference("driveMin", 0);
  public static final SN_Preference DRIVETRAIN_MAX = new SN_Preference("driveMax", 1);

  public static final SN_Preference DRIVETRAIN_TARGET_COUNT = new SN_Preference("driveTargetCount", 5000);
  public static final SN_Preference DRIVETRAIN_TARGET_TOLERANCE = new SN_Preference("driveTargetTolerance", 0);

  public static final SN_Preference DRIVETRAIN_TIMEOUT = new SN_Preference("driveTimeout", 10);
}