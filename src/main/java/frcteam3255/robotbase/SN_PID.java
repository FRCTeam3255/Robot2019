/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frcteam3255.robotbase;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frcteam3255.robotbase.SN_Preference;

/**
 * Add your docs here.
 */
public abstract class SN_PID extends PIDSubsystem {
  protected SN_Preference preferenceP;
  protected SN_Preference preferenceI;
  protected SN_Preference preferenceD;
  
  protected SN_Preference preferenceMaxChange;
  protected SN_Preference preferenceMin;
  protected SN_Preference preferenceMax;
  protected SN_Preference preferenceTargetCount;
  
  protected double output = 0.0;
  protected boolean outputValid = false;
  protected double outputMaxChange = 1.0;
  protected double previousOutput = 0.0;

  protected double tolerance = 0.0;
  protected int targetCounter = 0;

  protected double minPIDSpeed = 0.0;
  protected double maxPIDSpeed = 1.0;
  
  /**
   * SuperNURDs encapsulation of PIDSubsystem
   * @param name the name of the PID Subsystem
   */
  public SN_PID(String name) {
    // Intert a subsystem name and PID values here
    super(name, 0, 0, 0);

    this.setSetpoint(0.0);

    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }
  @Override
  public void enable() {
    this.getPIDController().setPID(
      preferenceP.getDouble(),
      preferenceI.getDouble(),
      preferenceD.getDouble());

      outputMaxChange = preferenceMaxChange.getDouble();
      previousOutput = 0.0;
      outputValid = false;

      minPIDSpeed = preferenceMin.getDouble();
      maxPIDSpeed = preferenceMax.getDouble();

      super.enable();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    //Change by Maximum Change
    if(Math.abs(output - previousOutput) > outputMaxChange) {
      if(output - previousOutput > 0) {
        output = previousOutput + outputMaxChange;
      }  
      else{
        output = previousOutput - outputMaxChange;
      }
    }
    else{
      previousOutput = output;
    }

    //Clamping
    if(output > 0) {
      if(output < minPIDSpeed) {
        output = minPIDSpeed;
      }
      else if(output > maxPIDSpeed) {
        output = maxPIDSpeed;
      }
    }
    else if(output < 0) {
      if(output > -minPIDSpeed) {
        output = -minPIDSpeed;
      }
      else if(output < -maxPIDSpeed) {
        output = -maxPIDSpeed;
      }
    }

    this.output = output;
    outputValid = true;
  }

  public double getOutput() {
    if((this.getPIDController().isEnabled() == false) || (outputValid == false)) {
      return 0.0;
    }
    return output;
  }

  public void setRawTolerance(double tolerance) {
    this.tolerance = tolerance;
  }

  public boolean onRawTarget() {
    if(Math.abs(getPIDController().getSetpoint() - returnPIDInput()) < tolerance){
      targetCounter = targetCounter + 1;
    }
    else{
      targetCounter = 0;
    }
    return(targetCounter >= preferenceTargetCount.getDouble());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}