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
public class Intake extends Subsystem {

	// TODO: These should be part of the cascade class, and the cascade can look at
	// the intake to determine switch states as needed
	public static enum fieldHeights {
		LOW, MED, HIGH, CSHIP, LOADED, FEEDER
	};

	// Talons
	private SN_TalonSRX cargoTalon = null;

	// Solenoids
	private DoubleSolenoid linkageSolenoid = null;
	private DoubleSolenoid cargoIntakeSolenoid = null;
	private DoubleSolenoid hatchFingerSolenoid = null;

	// Swtiches
	private DigitalInput hatchSwitch = null;
	private DigitalInput cargoSwitch = null;

	// Set the directions of the cargoIntake solenoid
	private static final Value cargoIntakeDeployedValue = Value.kReverse;
	private static final Value cargoIntakeRetractedValue = Value.kForward;

	private static final Value linkageDeployedValue = Value.kReverse;
	private static final Value linkageRetractedValue = Value.kForward;

	// Set the directions of the finger solenoid
	private static final Value fingerDeployedValue = Value.kReverse;
	private static final Value fingerRetractedValue = Value.kForward;

	/**
	 * Creates the devices used in the intake
	 */
	public Intake() {
		// Talons
		cargoTalon = new SN_TalonSRX(RobotMap.INTAKE_CARGO_TALON);

		// Solenoids
		linkageSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_LINKAGE_SOLENOID_A,
				RobotMap.INTAKE_LINKAGE_SOLENOID_B);
		hatchFingerSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_FINGER_SOLENOID_A,
				RobotMap.INTAKE_FINGER_SOLENOID_B);
		cargoIntakeSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.INTAKE_CARGO_INTAKE_SOLENOID_A,
				RobotMap.INTAKE_CARGO_INTAKE_SOLENOID_B);

		// Switches
		hatchSwitch = new DigitalInput(RobotMap.INTAKE_HATCH_SWITCH);
		cargoSwitch = new DigitalInput(RobotMap.INTAKE_CARGO_SWITCH);

		startMatch();
	}

	public void startMatch() {
		retractFinger();
		retractCargoIntake();
		deployLinkage();
	}

	/**
	 * Spin the intake to collect cargo
	 */
	public void collectCargo() {
		cargoTalon.set(-RobotPreferences.CARGO_COLLECT_SPEED.getValue());
	}

	/**
	 * Spin the intake to eject cargo
	 */
	public void shootCargo(double speed) {
		cargoTalon.set(-speed);
	}

	/**
	 * Hold the cargo by stopping the motors
	 */
	public void holdCargo() {
		cargoTalon.set(0.0);
	}

	/**
	 * Set the Linkage down
	 */
	public void deployLinkage() {
		linkageSolenoid.set(linkageDeployedValue);
	}

	public boolean isLinkageDeployed() {
		return linkageSolenoid.get() == linkageDeployedValue;
	}

	/**
	 * Set the Linkage up
	 */
	public void retractLinkage() {
		linkageSolenoid.set(linkageRetractedValue);
	}

	public boolean isLinkageRetracted() {
		return linkageSolenoid.get() == linkageRetractedValue;
	}

	public void toggleLinkage() {
		if (isLinkageRetracted() == true) {
			deployLinkage();
		} else {
			retractLinkage();
		}
	}

	/**
	 * Set the cargo intake down
	 */
	public void deployCargoIntake() {
		cargoIntakeSolenoid.set(cargoIntakeDeployedValue);
	}

	public boolean isCargoIntakeDeployed() {
		return cargoIntakeSolenoid.get() == cargoIntakeDeployedValue;
	}

	/**
	 * Set the cargo intake up
	 */
	public void retractCargoIntake() {
		cargoIntakeSolenoid.set(cargoIntakeRetractedValue);
	}

	public boolean isCargoIntakeRetracted() {
		return cargoIntakeSolenoid.get() == cargoIntakeRetractedValue;
	}

	/**
	 * Deploy the piston to grab the hatch from the floor
	 */
	public void deployFinger() {
		hatchFingerSolenoid.set(fingerDeployedValue);
	}

	/**
	 * Retracts the piston to grab the hatch from the floor
	 */
	public void retractFinger() {
		hatchFingerSolenoid.set(fingerRetractedValue);
	}

	public void toggleFinger() {
		if (isFingerRetracted() == true) {
			deployFinger();
		} else {
			retractFinger();
		}
	}

	public boolean isFingerDeployed() {
		return hatchFingerSolenoid.get() == fingerDeployedValue;
	}

	public boolean isFingerRetracted() {
		return hatchFingerSolenoid.get() == fingerRetractedValue;
	}

	public SN_DoublePreference getSetpoint(fieldHeights position) {

		switch (position) {
		case LOW:
			if (isCargoIntakeRetracted()) {
				return RobotPreferences.HATCH_POSITION_1;
			}
			return RobotPreferences.CARGO_POSITION_1;
		case MED:
			if (isCargoIntakeRetracted()) {
				return RobotPreferences.HATCH_POSITION_2;
			}
			return RobotPreferences.CARGO_POSITION_2;
		case HIGH:
			if (isCargoIntakeRetracted()) {
				return RobotPreferences.HATCH_POSITION_3;
			}
			return RobotPreferences.CARGO_POSITION_3;
		case CSHIP:
			if (isCargoIntakeRetracted()) {
				return RobotPreferences.HATCH_POSITION_1;
			}
			return RobotPreferences.CARGO_POSITION_SHIP;
		}
		return RobotPreferences.CARGO_POSITION_SHIP;
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