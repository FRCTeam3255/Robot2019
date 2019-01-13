/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frcteam3255.robotbase.RobotPreferences;

/**
 * Add your docs here.
 */
public abstract class DrivetrainDistancePID extends PIDSubsystem {
  protected String preferencePName = "";
  protected double preferencePDefault = 0;

  protected String preferenceIName = "";
  protected double preferenceIDefault = 0;
  
  protected String preferenceDName = "";
  protected double preferenceDDefault = 0;
  
  protected String preferenceMaxChangeName = "";
  protected double preferenceMaxChangeDefault = 0;
  
  protected String preferenceMinName = "";
  protected double preferenceMinDefault = 0;
  
  protected String preferenceMaxName = "";
  protected double preferenceMaxDefault = 0;

  protected String preferenceTargetCountName = "";
  protected double preferenceTargetCountDefault = 0;
  
  protected double output = 0.0;
  protected boolean outputValid = false;
  protected double outputMaxChange = 1.0;
  protected double previousOutput = 0.0;

  protected double tolerance = 0.0;
  protected int targetCounter = 0;

  protected double minPIDSpeed = 0.0;
  protected double maxPIDSpeed = 1.0;
  
  /**
   * Add your docs here.
   */
  public DrivetrainDistancePID() {
    // Intert a subsystem name and PID values here
    super("DrivetrainDistancePID", 0, 0, 0);

    this.setSetpoint(0.0);

    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }
  @Override
  public void enable() {
    this.getPIDController().setPID(
      RobotPreferences.getDouble(preferencePName, preferencePDefault),
      RobotPreferences.getDouble(preferenceIName, preferenceIDefault),
      RobotPreferences.getDouble(preferenceDName, preferenceDDefault));

      outputMaxChange = RobotPreferences.getDouble(preferenceMaxChangeName, preferenceMaxChangeDefault);
      previousOutput = 0.0;
      outputValid = false;

      minPIDSpeed = RobotPreferences.getDouble(preferenceMinName, preferenceMinDefault);
      maxPIDSpeed = RobotPreferences.getDouble(preferenceMaxName, preferenceMaxDefault);

      super.enable();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    //Change by Maximum CHange
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
    return(targetCounter >= RobotPreferences.getDouble(preferenceTargetCountName, preferenceTargetCountDefault));
  }
}