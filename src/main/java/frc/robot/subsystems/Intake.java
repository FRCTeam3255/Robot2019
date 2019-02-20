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
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

/**
 * Subsytem consisting of the intake devices and methoods
 */
public class Intake extends Subsystem {

	public static enum fieldHeights {
		LOW, MED, HIGH, CSHIP, LOADED
	};

	// Talons
	private SN_TalonSRX cargoTalon = null;

	// Solenoids
	private DoubleSolenoid ejectorSolenoid = null;
	private DoubleSolenoid intakeArmSolenoid = null;
	private DoubleSolenoid hatchHookSolenoid = null;

	// Swtiches
	private DigitalInput hatchSwitch = null;
	private DigitalInput cargoSwitch = null;

	// Set the directions of the eject solenoid
	private static final Value ejectorDeployedValue = Value.kReverse;
	private static final Value ejectorRetractedValue = Value.kForward;

	// Set the directions of the intakeArm solenoid
	private static final Value intakeDeployedValue = Value.kReverse;
	private static final Value intakeRetractedValue = Value.kForward;

	// Set the directions of the hook solenoid
	private static final Value hookDeployedValue = Value.kReverse;
	private static final Value hookRetractedValue = Value.kForward;

	/**
	 * Creates the devices used in the intake
	 */
	public Intake() {
		// Talons
		cargoTalon = new SN_TalonSRX(RobotMap.INTAKE_CARGO_TALON);

		// Solenoids
		ejectorSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_EJECTOR_SOLENOID_A,
				RobotMap.INTAKE_EJECTOR_SOLENOID_B);
		intakeArmSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_ARM_SOLENOID_A,
				RobotMap.INTAKE_ARM_SOLENOID_B);
		hatchHookSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_HOOK_SOLENOID_A,
				RobotMap.INTAKE_HOOK_SOLENOID_B);

		// Switches
		hatchSwitch = new DigitalInput(RobotMap.INTAKE_HATCH_SWITCH);
		cargoSwitch = new DigitalInput(RobotMap.INTAKE_CARGO_SWITCH);

		retractEjector();
		retractHook();
		deployIntake();
	}

	/**
	 * Spin the intake to collect cargo
	 */
	public void collectCargo() {
		cargoTalon.set(RobotPreferences.CARGO_COLLECT_SPEED.getValue());
	}

	/**
	 * Spin the intake to eject cargo
	 */
	public void shootCargo() {
		cargoTalon.set(RobotPreferences.CARGO_SHOOT_SPEED.getValue());
	}

	/**
	 * Hold the cargo by stopping the motors
	 */
	public void holdCargo() {
		cargoTalon.set(0.0);
	}

	/**
	 * Set the intake down
	 */
	public void deployIntake() {
		intakeArmSolenoid.set(intakeDeployedValue);
	}

	public boolean isIntakeDeployed() {
		return intakeArmSolenoid.get() == intakeDeployedValue;
	}

	/**
	 * Set the intake up
	 */
	public void retractIntake() {
		intakeArmSolenoid.set(intakeRetractedValue);
	}

	public boolean isIntakeRetracted() {
		return intakeArmSolenoid.get() == intakeRetractedValue;
	}

	/**
	 * Deploy the piston to grab the hatch from the floor
	 */
	public void deployHook() {
		hatchHookSolenoid.set(hookDeployedValue);
	}

	/**
	 * Retracts the piston to grab the hatch from the floor
	 */
	public void retractHook() {
		hatchHookSolenoid.set(hookRetractedValue);
	}

	public void toggleHook() {
		if (isHookRetracted() == true) {
			deployHook();
		} else {
			retractHook();
		}
	}

	public boolean isHookDeployed() {
		return hatchHookSolenoid.get() == hookDeployedValue;
	}

	public boolean isHookRetracted() {
		return hatchHookSolenoid.get() == hookRetractedValue;
	}

	// /**
	// * Eject the hatch by firing the pistons
	// */
	public void deployEjector() {
		ejectorSolenoid.set(ejectorDeployedValue);
	}

	// /**
	// * Retract the hatch ejecting pistons
	// */
	public void retractEjector() {
		ejectorSolenoid.set(ejectorRetractedValue);
	}

	public boolean isEjectorDeployed() {
		return ejectorSolenoid.get() == ejectorDeployedValue;
	}

	public boolean isEjectorRetracted() {
		return ejectorSolenoid.get() == ejectorRetractedValue;
	}

	public SN_DoublePreference getSetpoint(fieldHeights position) {

		switch (position) {
		case LOW:
			if (isIntakeRetracted()) {
				return RobotPreferences.HATCH_POSITION_1;
			}
			return RobotPreferences.CARGO_POSITION_1;
		case MED:
			if (isIntakeRetracted()) {
				return RobotPreferences.HATCH_POSITION_2;
			}
			return RobotPreferences.CARGO_POSITION_2;
		case HIGH:
			if (isIntakeRetracted()) {
				return RobotPreferences.HATCH_POSITION_3;
			}
			return RobotPreferences.CARGO_POSITION_3;
		case CSHIP:
			if (isIntakeRetracted()) {
				return RobotPreferences.HATCH_POSITION_1;
			}
			return RobotPreferences.CARGO_POSITION_SHIP;
		case LOADED:
			return RobotPreferences.HATCH_POSITION_LOADED;
		default:
			return RobotPreferences.CASCADE_BOTTOM;
		}
	}

	/**
	 * @return Check if the hatch triggered the hatch switch
	 */
	public boolean isHatchCollected() {
		return !hatchSwitch.get();
	}

	/**
	 * @return Check if the cargo triggered the cargo swtich
	 */
	public boolean isCargoCollected() {
		return !cargoSwitch.get();
	}

	@Override
	public void initDefaultCommand() {
	}
}