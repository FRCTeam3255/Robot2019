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
  protected SN_DoublePreference pref_p = new SN_DoublePreference("SNPID_P", 1.0);
  protected SN_DoublePreference pref_i = new SN_DoublePreference("SNPID_I", 0.0);
  protected SN_DoublePreference pref_d = new SN_DoublePreference("SNPID_D", 0.0);

  protected SN_DoublePreference pref_setPoint = new SN_DoublePreference("SNPID_setPoint", 0.0);
  protected SN_DoublePreference pref_tolerance = new SN_DoublePreference("SNPID_tol", 0.0);
  protected SN_IntPreference pref_targetCount = new SN_IntPreference("SNPID_targetCount", 1);

  protected SN_DoublePreference pref_minOutput = new SN_DoublePreference("SNPID_minOutput", 0.0);
  protected SN_DoublePreference pref_maxOutput = new SN_DoublePreference("SNPID_maxOutput", 1.0);
  protected SN_DoublePreference pref_maxOutputChange = new SN_DoublePreference("SNPID_maxOutputChange", 1.0);
  protected SN_DoublePreference pref_defaultOutput = new SN_DoublePreference("SNPID_defaultOuput", 0.0);

  // values used to cache target constraint preferences that will be used while
  // the PID is enabled
  private double cached_tolerance = pref_tolerance.getValue();
  private double cached_targetCount = pref_targetCount.getValue();

  // values used to cache output constraint preferences that will be used while
  // the PID is enabled
  private double cached_minOutput = pref_minOutput.getValue();
  private double cached_maxOutput = pref_maxOutput.getValue();
  private double cached_maxOutputChange = pref_maxOutputChange.getValue();
  private double cached_defaultOutput = pref_defaultOutput.getValue();

  protected boolean inputValid = false;
  protected boolean outputValid = false;
  protected double output = 0.0;
  protected double previousOutput = 0.0;

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
  public void setMinOutput(SN_DoublePreference minOutput) {
    pref_minOutput = minOutput;
  }

  public void setMaxOutput(SN_DoublePreference maxOutput) {
    pref_maxOutput = maxOutput;
  }

  public void setMaxOutputChant(SN_DoublePreference maxOutputChange) {
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
    cached_minOutput = pref_minOutput.getValue();
    cached_maxOutput = pref_maxOutput.getValue();
    cached_maxOutputChange = pref_maxOutputChange.getValue();
    cached_defaultOutput = pref_defaultOutput.getValue();

    System.out.println("tol = " + cached_tolerance);
    System.out.println("target count = " + cached_targetCount);
    System.out.println("min output = " + cached_minOutput);
    System.out.println("max out = " + cached_maxOutput);
    System.out.println("max out change = " + cached_maxOutputChange);
    System.out.println("default = " + cached_defaultOutput);

    previousOutput = 0.0;
    inputValid = false;
    outputValid = false;

    super.enable();
  }

  /**
   * Use a PID output value that abides by change and and clamp constraints when
   * the input is ready to produce a valid output
   */
  @Override
  protected void usePIDOutput(double output) {
    if (inputValid == false) {
      this.output = cached_defaultOutput;
      outputValid = false;
      return;
    }

    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    // Change by Maximum Change
    if (Math.abs(output - previousOutput) > cached_maxOutputChange) {
      if (output - previousOutput > 0) {
        output = previousOutput + cached_maxOutputChange;
      } else {
        output = previousOutput - cached_maxOutputChange;
      }
    } else {
      previousOutput = output;
    }

    // Clamping
    if (output > 0) {
      if (output < cached_minOutput) {
        output = cached_minOutput;
      } else if (output > cached_maxOutput) {
        output = cached_maxOutput;
      }
    } else if (output < 0) {
      if (output > -cached_minOutput) {
        output = -cached_minOutput;
      } else if (output < -cached_maxOutput) {
        output = -cached_maxOutput;
      }
    }

    this.output = output;
    outputValid = true;
  }

  /**
   * @return Get PID output. If the controller is not enabled or the output is not
   *         valid, use the default
   */
  public double getOutput() {
    System.out.println("get output prints");
    System.out.println("isEnabled" + this.getPIDController().isEnabled());
    System.out.println("outputval" + outputValid);
    System.out.println("cashed" + cached_defaultOutput);
    System.out.println("output" + output);
    System.out.println();

    if ((this.getPIDController().isEnabled() == false) || (outputValid == false)) {
      return cached_defaultOutput;
    }
    return output;
  }

  /**
   * @return Check if we stayed within the setpoint tolerance for the set amount
   *         of target counts
   */
  public boolean onRawTarget() {
    double inputValue = returnPIDInput();
    System.out.println("on raw target data");
    System.out.println("input value" + inputValue);
    System.out.println("input valid" + inputValid);
    System.out.println("setpoint" + getPIDController().getSetpoint());
    System.out.println("targetCounter" + targetCounter);
    System.out.println("cached targetCount" + cached_targetCount);
    System.out.println("cached tol" + cached_tolerance);
    System.out.println("\n");

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