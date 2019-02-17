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
import frc.robot.commands.Intake.WaitForHatchAndPickUp;
import frcteam3255.robotbase.SN_TalonSRX;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

/**
 * Subsytem consisting of the intake devices and methoods
 */
public class Intake extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private SN_TalonSRX cargoTalon = null;

	// Solenoids
	private DoubleSolenoid ejectSolenoid = null;
	private DoubleSolenoid intakeArmSolenoid = null;
	private DoubleSolenoid hatchHookSolenoid = null;

	// Swtiches
	private DigitalInput hatchSwitch = null;
	private DigitalInput cargoSwitch = null;

	/**
	 * Creates the devices used in the intake
	 */
	public Intake() {
		cargoTalon = new SN_TalonSRX(RobotMap.INTAKE_CARGO_TALON);

		// Solenoids
		ejectSolenoid = new DoubleSolenoid(RobotMap.CASCADE_PCM, RobotMap.INTAKE_EJECT_SOLENOID_A,
				RobotMap.INTAKE_EJECT_SOLENOID_B);
		intakeArmSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_ARM_SOLENOID_A,
				RobotMap.INTAKE_ARM_SOLENOID_B);
		hatchHookSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_HOOK_SOLENOID_A,
				RobotMap.INTAKE_HOOK_SOLENOID_B);

		// Switches
		hatchSwitch = new DigitalInput(RobotMap.INTAKE_HATCH_SWITCH);
		cargoSwitch = new DigitalInput(RobotMap.INTAKE_CARGO_SWITCH);

		intakeDeploy();
		deployHook();
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
	public void shootCargo() {
		cargoTalon.set(RobotPreferences.EJECT_CARGO_SPEED.getValue());
	}

	/**
	 * Hold the cargo by stopping the motors
	 */
	public void holdCargo() {
		cargoTalon.set(0.0);
	}

	public void intakeDeploy() {
		intakeArmSolenoid.set(Value.kReverse);
	}

	public void intakeRetract() {
		intakeArmSolenoid.set(Value.kForward);
	}

	public boolean isIntakeRetracted() {
		return intakeArmSolenoid.get() == Value.kForward;
	}

	/**
	 * Deploy the piston to grab the hatch from the floor
	 */
	public void deployHook() {
		hatchHookSolenoid.set(Value.kReverse);
	}

	/**
	 * Retracts the piston to grab the hatch from the floor
	 */
	public void retractHook() {
		hatchHookSolenoid.set(Value.kForward);
	}

	public void toggleHook() {
		if (isIntakeRetracted() == true) {
			deployHook();
		} else {
			retractHook();
		}
	}

	public boolean isHookDeployed() {
		return hatchHookSolenoid.get() == Value.kReverse;
	}

	// /**
	// * Eject the hatch by firing the pistons
	// */
	public void ejectHatch() {
		ejectSolenoid.set(Value.kReverse);
	}

	// /**
	// * Retract the hatch ejecting pistons
	// */
	public void reloadHatch() {
		ejectSolenoid.set(Value.kForward);
	}

	public SN_DoublePreference getP1Setpoint() {
		if (isIntakeRetracted()) {
			return RobotPreferences.HATCH_POSITION_1;
		} else {
			return RobotPreferences.CARGO_POSITION_1;
		}
	}

	public SN_DoublePreference getP2Setpoint() {
		if (isIntakeRetracted()) {
			return RobotPreferences.HATCH_POSITION_2;
		} else {
			return RobotPreferences.CARGO_POSITION_2;
		}
	}

	public SN_DoublePreference getP3Setpoint() {
		if (isIntakeRetracted()) {
			return RobotPreferences.HATCH_POSITION_3;
		} else {
			return RobotPreferences.CARGO_POSITION_3;
		}
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
		return !cargoSwitch.get();
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new WaitForHatchAndPickUp());
	}
}