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
 * Subsytem consisting of the intake devices and methoods
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private SN_TalonSRX cargoTalon = null;

  // Solenoids
  // private DoubleSolenoid ejectSolenoid = null;
  // private DoubleSolenoid hatchDeploySolenoid = null;
  // private DoubleSolenoid hatchIntakeSolenoid = null;
  // private DoubleSolenoid cargoSolenoid = null;
  // private DoubleSolenoid deploySolenoid = null;

  // Swtiches
  private DigitalInput hatchSwitch = null;
  private DigitalInput cargoSwitch = null;

  /**
   * Creates the devices used in the intake
   */
  public Intake() {
    cargoTalon = new SN_TalonSRX(RobotMap.INTAKE_CARGO_TALON);

    // Solenoids
    // ejectSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM,
    // RobotMap.INTAKE_EJECT_SOLENOID_A,
    // RobotMap.INTAKE_EJECT_SOLENOID_B);
    // hatchDeploySolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM,
    // RobotMap.INTAKE_HATCH_DEPLOY_SOLENOID_A,
    // RobotMap.INTAKE_HATCH_DEPLOY_SOLENOID_B);
    // hatchIntakeSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM,
    // RobotMap.INTAKE_HATCH_INTAKE_SOLENOID_A,
    // RobotMap.INTAKE_HATCH_INTAKE_SOLENOID_B);
    // cargoSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM,
    // RobotMap.INTAKE_CARGO_SOLENOID_A,
    // RobotMap.INTAKE_CARGO_SOLENOID_B);
    // deploySolenoid = new DoubleSolenoid(RobotMap.CASCADE_PCM,
    // RobotMap.INTAKE_DEPLOY_SOLENOID_A,
    // RobotMap.INTAKE_DEPLOY_SOLENOID_B);

    // Switches
    hatchSwitch = new DigitalInput(RobotMap.INTAKE_HATCH_SWITCH);
    cargoSwitch = new DigitalInput(RobotMap.INTAKE_CARGO_SWITCH);
  }

  /**
   * Spin the intake to collect cargo
   */
  public void intakeCargo() {
    cargoTalon.set(RobotPreferences.INTAKE_CARGO_SPEED.getValue());
  }

  /**
   * Spin the intake to eject cargo
   */
  public void ejectCargo() {
    cargoTalon.set(RobotPreferences.EJECT_CARGO_SPEED.getValue());
  }

  /**
   * Hold the cargo by stopping the motors
   */
  public void holdCargo() {
    cargoTalon.set(0.0);
  }

  /**
   * Set the intake on the floor
   */
  public void deployIntake() {
    // deploySolenoid.set(Value.kForward);
  }

  /**
   * Lift the intake vertically into the robot
   */
  public void retractIntake() {
    // deploySolenoid.set(Value.kReverse);
  }

  /**
   * Set the Hatch Intake parallel to the floor
   */
  public void deployHatch() {
    // hatchDeploySolenoid.set(Value.kForward);
  }

  /**
   * Lift the Hatch Intake vertically to the robot
   */
  public void retractHatch() {
    // hatchDeploySolenoid.set(Value.kReverse);
  }

  /**
   * Set the Cargo Intake Parallel to the floor
   */
  public void deployCargo() {
    // cargoSolenoid.set(Value.kForward);
  }

  /**
   * Lift the Cargo Intake vertically to the robot
   */
  public void retractCargo() {
    // cargoSolenoid.set(Value.kReverse);
  }

  /**
   * Deploy the piston to grab the hatch from the floor
   */
  public void reachHatch() {
    // hatchIntakeSolenoid.set(Value.kForward);
  }

  /**
   * Retracts the piston to grab the hatch from the floor
   */
  public void grabHatch() {
    // hatchIntakeSolenoid.set(Value.kReverse);
  }

  /**
   * Eject the hatch by firing the pistons
   */
  public void ejectHatch() {
    // ejectSolenoid.set(Value.kForward);
  }

  /**
   * Retract the hatch ejecting pistons
   */
  public void reloadHatch() {
    // ejectSolenoid.set(Value.kReverse);
  }

  /**
   * @return Check if the hatch triggered the hatch switch
   */
  public boolean isHatchCollected() {
    return hatchSwitch.get();
  }

  /**
   * @return Check if the cargo triggered the cargo swtich
   */
  public boolean isCargoCollected() {
    return cargoSwitch.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}