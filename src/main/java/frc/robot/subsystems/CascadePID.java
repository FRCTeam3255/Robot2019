/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frcteam3255.robotbase.SN_PID;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;
import frcteam3255.robotbase.Preferences.SN_IntPreference;

/**
 * PID class based off the cascade distance
 */
public class CascadePID extends SN_PID {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  // Cascade PID
  public static final SN_DoublePreference CASCADE_P = new SN_DoublePreference("cascadeP", 0.12);
  public static final SN_DoublePreference CASCADE_I = new SN_DoublePreference("cascadeI", 0.0);
  public static final SN_DoublePreference CASCADE_D = new SN_DoublePreference("cascadeD", 0.0);
  public static final SN_DoublePreference CASCADE_TOL = new SN_DoublePreference("cascadeTol", 1.0);
  public static final SN_IntPreference CASCADE_TARGET_COUNT = new SN_IntPreference("cascadeTargetCount", 1);
  public static final SN_DoublePreference CASCADE_MINOUT = new SN_DoublePreference("cascadeMinSpeed", 0);
  public static final SN_DoublePreference CASCADE_MAXOUT = new SN_DoublePreference("cascadeMaxSpeed", 1);
  public static final SN_DoublePreference CASCADE_MAXCHANGE = new SN_DoublePreference("cascadeMaxChange", 1.0);

  /**
   * Creates a Cascade PID loop and sets PID values
   */
  public CascadePID() {
    super();

    setPID(CASCADE_P, CASCADE_I, CASCADE_D);
    setTolerance(CASCADE_TOL);
    setTargetCount(CASCADE_TARGET_COUNT);
    setMinOutput(CASCADE_MINOUT);
    setMaxOutput(CASCADE_MAXOUT);
    setMaxOutputChange(CASCADE_MAXCHANGE);
  }

  /**
   * @return Inputs the cascade distance
   */
  @Override
  protected double returnPIDInput() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    inputValid = true;
    double d = Robot.m_cascade.getLiftEncoderDistance();
    System.err.println("CascadePID.returnPIDInput: d = " + d);
    return d;
  }
}
