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

  private SN_TalonSRX intakeTalon = null;

  // Solenoids
  private DoubleSolenoid ejectSolenoid = null;
  private DoubleSolenoid hatchSolenoid = null;
  private DoubleSolenoid deploySolenoid = null;

  // Swtiches
  private DigitalInput hatchSwitch = null;
  private DigitalInput cargoSwitch = null;

  /**
   * Creates the devices used in the intake
   */
  public Intake() {
    intakeTalon = new SN_TalonSRX(RobotMap.INTAKE_TALON);

    // Solenoids
    // ejectSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM,
    // RobotMap.INTAKE_EJECT_SOLENOID_A,
    // RobotMap.INTAKE_EJECT_SOLENOID_B);
    hatchSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_HATCH_SOLENOID_A,
        RobotMap.INTAKE_HATCH_SOLENOID_B);
    // deploySolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM,
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
    intakeTalon.set(RobotPreferences.INTAKE_CARGO_SPEED.getValue());
  }

  /**
   * Spin the intake to eject cargo
   */
  public void ejectCargo() {
    intakeTalon.set(RobotPreferences.EJECT_CARGO_SPEED.getValue());
  }

  /**
   * Hold the cargo by stopping the motors
   */
  public void holdCargo() {
    intakeTalon.set(0.0);
  }

  /**
   * Set the intake on the floor
   */
  public void deployIntake() {
    deploySolenoid.set(Value.kForward);
  }

  /**
   * Lift the intake vertically into the robot
   */
  public void retractIntake() {
    deploySolenoid.set(Value.kReverse);
  }

  public void deployHatch() {
    hatchSolenoid.set(Value.kForward);
  }

  public void retractHatch() {
    hatchSolenoid.set(Value.kReverse);
  }

  /**
   * Eject the hatch by firing the pistons
   */
  public void ejectHatch() {
    ejectSolenoid.set(Value.kForward);
  }

  /**
   * Retract the hatch ejecting pistons
   */
  public void reloadHatch() {
    ejectSolenoid.set(Value.kReverse);
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