/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frcteam3255.robotbase;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;
import frcteam3255.robotbase.Preferences.SN_IntPreference;

/**
 * SuperNURDs PID base class
 */
public abstract class SN_PID extends PIDSubsystem {
  protected static SN_DoublePreference default_p = new SN_DoublePreference("SNPID_P", 1.0);
  protected static SN_DoublePreference default_i = new SN_DoublePreference("SNPID_I", 0.0);
  protected static SN_DoublePreference default_d = new SN_DoublePreference("SNPID_D", 0.0);

  protected static SN_DoublePreference default_setPoint = new SN_DoublePreference("SNPID_setPoint", 0.0);
  protected static SN_DoublePreference default_tolerance = new SN_DoublePreference("SNPID_tol", 1.0);
  protected static SN_IntPreference default_targetCount = new SN_IntPreference("SNPID_targetCount", 1);

  protected static SN_DoublePreference default_minOutputNegative = new SN_DoublePreference("SNPID_minOutputNegative",
      0.0);
  protected static SN_DoublePreference default_minOutputPositive = new SN_DoublePreference("SNPID_minOutputPositive",
      0.0);
  protected static SN_DoublePreference default_maxOutputNegative = new SN_DoublePreference("SNPID_maxOutputNegative",
      1.0);
  protected static SN_DoublePreference default_maxOutputPositive = new SN_DoublePreference("SNPID_maxOutputPositive",
      1.0);
  protected static SN_DoublePreference default_maxOutputChange = new SN_DoublePreference("SNPID_maxOutputChange", 1.0);
  protected static SN_DoublePreference default_defaultOutput = new SN_DoublePreference("SNPID_defaultOuput", 0.0);

  protected SN_DoublePreference pref_p = default_p;
  protected SN_DoublePreference pref_i = default_i;
  protected SN_DoublePreference pref_d = default_d;

  protected SN_DoublePreference pref_setPoint = default_setPoint;
  protected SN_DoublePreference pref_tolerance = default_tolerance;
  protected SN_IntPreference pref_targetCount = default_targetCount;

  protected SN_DoublePreference pref_minOutputNegative = default_minOutputNegative;
  protected SN_DoublePreference pref_minOutputPositive = default_minOutputPositive;
  protected SN_DoublePreference pref_maxOutputNegative = default_maxOutputNegative;
  protected SN_DoublePreference pref_maxOutputPositive = default_maxOutputPositive;
  protected SN_DoublePreference pref_maxOutputChange = default_maxOutputChange;
  protected SN_DoublePreference pref_defaultOutput = default_defaultOutput;

  // values used to cache target constraint preferences that will be used while
  // the PID is enabled
  private double cached_tolerance = pref_tolerance.getValue();
  private double cached_targetCount = pref_targetCount.getValue();

  // values used to cache output constraint preferences that will be used while
  // the PID is enabled
  private double cached_minOutputNegative = pref_minOutputNegative.getValue();
  private double cached_minOutputPositive = pref_minOutputPositive.getValue();
  private double cached_maxOutputNegative = pref_maxOutputNegative.getValue();
  private double cached_maxOutputPositive = pref_maxOutputPositive.getValue();
  private double cached_maxOutputChange = pref_maxOutputChange.getValue();
  private double cached_defaultOutput = pref_defaultOutput.getValue();

  protected boolean inputValid = false;
  protected boolean outputValid = false;
  protected double output = 0.0;

  protected int targetCounter = 0;

  /**
   * SuperNURDs encapsulation of PIDSubsystem
   * 
   * @param name the name of the PID Subsystem
   */
  public SN_PID() {
    // Intert a subsystem name and PID values here
    super(0, 0, 0);
  }

  /**
   * 
   * @param p
   * @param i
   * @param d
   */
  public void setPID(SN_DoublePreference p, SN_DoublePreference i, SN_DoublePreference d) {
    pref_p = p;
    pref_i = i;
    pref_d = d;
  }

  // Set targeting constraints
  public void setSetpoint(SN_DoublePreference setPoint) {
    pref_setPoint = setPoint;
  }

  public double getSetpoint() {
    return pref_setPoint.getValue();
  }

  public void setTolerance(SN_DoublePreference tolerance) {
    pref_tolerance = tolerance;
  }

  public void setTargetCount(SN_IntPreference targetCount) {
    pref_targetCount = targetCount;
  }

  // Set output constraints
  public void setMinOutputPositive(SN_DoublePreference minOutputPositive) {
    pref_minOutputPositive = minOutputPositive;
  }

  public void setMinOutputNegative(SN_DoublePreference minOutputNegative) {
    pref_minOutputNegative = minOutputNegative;
  }

  public void setMinOutput(SN_DoublePreference minOutput) {
    setMinOutputPositive(minOutput);
    setMinOutputPositive(minOutput);
  }

  public void setMaxOutputPositive(SN_DoublePreference maxOutputPositive) {
    pref_maxOutputPositive = maxOutputPositive;
  }

  public void setMaxOutputNegative(SN_DoublePreference maxOutputNegative) {
    pref_maxOutputNegative = maxOutputNegative;
  }

  public void setMaxOutput(SN_DoublePreference maxOutput) {
    setMaxOutputPositive(maxOutput);
    setMaxOutputNegative(maxOutput);
  }

  public void setMaxOutputChange(SN_DoublePreference maxOutputChange) {
    pref_maxOutputChange = maxOutputChange;
  }

  public void setDefaultOutput(SN_DoublePreference defaultOutput) {
    pref_defaultOutput = defaultOutput;
  }

  /**
   * Sets the:
   * <ul>
   * <li>PID values</li>
   * <li>Setpoint constraints</li>
   * <li>Target constraints</li>
   * <li>Output constraints</li>
   * </ul>
   * before enabling the PID controller
   */
  @Override
  public void enable() {
    PIDController controller = getPIDController();

    controller.setPID(pref_p.getValue(), pref_i.getValue(), pref_d.getValue());
    controller.setSetpoint(pref_setPoint.getValue());

    // cache the target constraint preferences
    cached_tolerance = pref_tolerance.getValue();
    cached_targetCount = pref_targetCount.getValue();

    // cache the output constraint preferences
    cached_minOutputNegative = pref_minOutputNegative.getValue();
    cached_minOutputPositive = pref_minOutputPositive.getValue();
    cached_maxOutputNegative = pref_maxOutputNegative.getValue();
    cached_maxOutputPositive = pref_maxOutputPositive.getValue();
    cached_maxOutputChange = pref_maxOutputChange.getValue();
    cached_defaultOutput = pref_defaultOutput.getValue();

    output = 0.0;
    inputValid = false;
    outputValid = false;

    controller.reset();
    super.enable();
  }

  /**
   * Use a PID output value that abides by change and and clamp constraints when
   * the input is ready to produce a valid output
   */
  @Override
  protected void usePIDOutput(double requestedOutput) {
    double previousOutput = output;

    double newOutput = requestedOutput;
    boolean newOutputValid = true;

    if (inputValid == false) {
      newOutput = cached_defaultOutput;
      newOutputValid = false;
    }

    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    // Change by Maximum Change
    if (Math.abs(newOutput - previousOutput) > cached_maxOutputChange) {
      if (newOutput - previousOutput > 0) {
        newOutput = previousOutput + cached_maxOutputChange;
      } else {
        newOutput = previousOutput - cached_maxOutputChange;
      }
    }

    // Clamping
    if (newOutput > 0) {
      if (newOutput < cached_minOutputPositive) {
        newOutput = cached_minOutputPositive;
      } else if (newOutput > cached_maxOutputPositive) {
        newOutput = cached_maxOutputPositive;
      }
    } else if (newOutput < 0) {
      if (newOutput > -cached_minOutputNegative) {
        newOutput = -cached_minOutputNegative;
      } else if (newOutput < -cached_maxOutputNegative) {
        newOutput = -cached_maxOutputNegative;
      }
    }

    output = newOutput;
    outputValid = newOutputValid;
  }

  /**
   * @return Get PID output. If the controller is not enabled or the output is not
   *         valid, use the default
   */
  public double getOutput() {
    if ((this.getPIDController().isEnabled() == false) || (outputValid == false)) {
      return cached_defaultOutput;
    }
    return output;
  }

  public boolean isInputValid() {
    return inputValid;
  }

  public boolean isOutputValid() {
    return outputValid;
  }

  /**
   * @return Check if we stayed within the setpoint tolerance for the set amount
   *         of target counts
   */
  public boolean onRawTarget() {
    double inputValue = returnPIDInput();

    if (inputValid == false) {
      targetCounter = 0;
      return false;
    }

    if (Math.abs(getPIDController().getSetpoint() - inputValue) < cached_tolerance) {
      targetCounter = targetCounter + 1;
    } else {
      targetCounter = 0;
    }

    return (targetCounter >= cached_targetCount);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}