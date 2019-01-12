/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frcteam3255.robotbase.SN_TalonSRX;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
 private SN_TalonSRX intakeTalon = null;
 private DoubleSolenoid leftSolenoid = null;
 private DoubleSolenoid rightSolenoid = null;

public Intake(){
intakeTalon = new SN_TalonSRX(RobotMap.INTAKE_TALON); 
leftSolenoid = new DoubleSolenoid(RobotMap.INTAKE_LEFT_SOLENOID_A, RobotMap.INTAKE_LEFT_SOLENOID_B);
rightSolenoid = new DoubleSolenoid(RobotMap.INTAKE_RIGHT_SOLENOID_A, RobotMap.INTAKE_RIGHT_SOLENOID_B);
}

public void deployLeftSolenoid(){
  leftSolenoid.set(Value.kForward);
}

public void deployRightSolenoid(){
  rightSolenoid.set(Value.kForward);
}
public void retractLeftSolenoid(){
  leftSolenoid.set(Value.kReverse);
}

public void retractRightSolenoid(){
  rightSolenoid.set(Value.kReverse);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}