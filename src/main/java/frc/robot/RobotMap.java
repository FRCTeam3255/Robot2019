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

  //Talons
  public static final int DRIVETRAIN_LEFT_FRONT_TALON = 1;
  public static final int DRIVETRAIN_LEFT_MID_TALON = 2;
  public static final int DRIVETRAIN_LEFT_BACK_TALON = 3;
  public static final int DRIVETRAIN_RIGHT_FRONT_TALON = 4;
  public static final int DRIVETRAIN_RIGHT_MID_TALON = 5;
  public static final int DRIVETRAIN_RIGHT_BACK_TALON = 6;
  public static final int INTAKE_TALON = 7;

  //Joysticks
  public static final int JOYSTICK_DRIVER = 0;
  public static final int JOYSTICK_MANIPULATOR =  1;

  public static final int DRIVER_MOVE_AXIS = 1;
  public static final int DRIVER_ROTATE_AXIS = 2;

  //Encoders
  public static final int DRIVETRAIN_ENCODER_B = 0;
  public static final int DRIVETRAIN_ENCODER_A = 1;

  //Solenoids
  public static final int INTAKE_LEFT_SOLENOID_A = 0;
  public static final int INTAKE_LEFT_SOLENOID_B = 1;
  public static final int INTAKE_RIGHT_SOLENOID_A = 2;
  public static final int INTAKE_RIGHT_SOLENOID_B = 3;
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}