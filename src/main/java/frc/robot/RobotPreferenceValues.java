/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalSource;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotPreferenceValues {
  // Drivetrain preferences
  public static final String DRIVETRAIN_PULSES_PER_FOOT_NAME = "driveTrainPulsesPerFoot";
  public static final double DRIVETRAIN_PULSES_PER_FOOT_DEFAULT = 5;

  public static final String DRIVETRAIN_P_NAME = "driveP";
  public static final double DRIVETRAIN_P_DEFAULT = 1;

  public static final String DRIVETRAIN_I_NAME = "driveI";
  public static final double DRIVETRAIN_I_DEFAULT = 0;

  public static final String DRIVETRAIN_D_NAME = "driveD";
  public static final double DRIVETRAIN_D_DEFAULT = 0;

  public static final String DRIVETRAIN_MAX_CHANGE_NAME = "driveMaxChange";
  public static final double DRIVETRAIN_MAX_CHANGE_DEFAULT = 1;

  public static final String DRIVETRAIN_MIN_NAME = "driveMin";
  public static final double DRIVETRAIN_MIN_DEFAULT = 0;

  public static final String DRIVETRAIN_MAX_NAME = "driveMax";
  public static final double DRIVETRAIN_MAX_DEFAULT = 1;

  public static final String DRIVETRAIN_TARGET_COUNT_NAME = "driveTargetCount";
  public static final double DRIVETRAIN_TARGET_COUNT_DEFAULT = 5000;

  public static final String DRIVETRAIN_TARGET_TOLERANCE_NAME = "driveTargetTolerance";
  public static final double DRIVETRAIN_TARGET_TOLERANCE_DEFAULT = 0;

  public static final String DRIVETRAIN_TIMEOUT_NAME = "driveTimeout";
  public static final double DRIVETRAIN_TIMEOUT_DEFAULT = 10;
}