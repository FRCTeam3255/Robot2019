/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frcteam3255.robotbase.SN_TalonSRX;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private SN_TalonSRX intakeTalon = null;

  private DoubleSolenoid ejectSolenoid = null;
  private DoubleSolenoid hatchSolenoid = null;
  private DoubleSolenoid deploySolenoid = null;
  private DigitalInput hatchSwitch = null;
  private DigitalInput cargoSwitch = null;

  public Intake() {
    intakeTalon = new SN_TalonSRX(RobotMap.INTAKE_TALON);

    // ejectSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM,
    // RobotMap.INTAKE_EJECT_SOLENOID_A,
    // RobotMap.INTAKE_EJECT_SOLENOID_B);
    // hatchSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM,
    // RobotMap.INTAKE_HATCH_SOLENOID_A,
    // RobotMap.INTAKE_HATCH_SOLENOID_B);
    // deploySolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM,
    // RobotMap.INTAKE_DEPLOY_SOLENOID_A,
    // RobotMap.INTAKE_DEPLOY_SOLENOID_B);

    hatchSwitch = new DigitalInput(RobotMap.INTAKE_HATCH_SWITCH);
    cargoSwitch = new DigitalInput(RobotMap.INTAKE_CARGO_SWITCH);
  }

  public void intakeCargo() {
    intakeTalon.set(RobotPreferences.INTAKE_CARGO_SPEED.getValue());
  }

  public void ejectCargo() {
    intakeTalon.set(RobotPreferences.EJECT_CARGO_SPEED.getValue());
  }

  public void holdCargo() {
    intakeTalon.set(0.0);
  }

  public void deployIntake() {
    deploySolenoid.set(Value.kForward);
  }

  public void retractIntake() {
    deploySolenoid.set(Value.kReverse);
  }

  public void deployHatch() {
    hatchSolenoid.set(Value.kForward);
  }

  public void retractHatch() {
    hatchSolenoid.set(Value.kReverse);
  }

  public void ejectHatch() {
    ejectSolenoid.set(Value.kForward);
  }

  public void reloadHatch() {
    ejectSolenoid.set(Value.kReverse);
  }

  public boolean isHatchCollected() {
    return hatchSwitch.get();
  }

  public boolean isCargoCollected() {
    return cargoSwitch.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}