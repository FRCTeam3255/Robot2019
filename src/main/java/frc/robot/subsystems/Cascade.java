/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frc.robot.commands.Cascade.CascadeResetEncoder;
import frcteam3255.robotbase.SN_TalonSRX;

/**
 * Subsytem containing the cascade devices and methoods
 */
public class Cascade extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	// Talons
	private SN_TalonSRX leftFrontTalon = null;
	private SN_TalonSRX leftBackTalon = null;
	private SN_TalonSRX rightFrontTalon = null;
	private SN_TalonSRX rightBackTalon = null;

	// Encoders
	private Encoder liftEncoder = null;

	// Solenoids
	private DoubleSolenoid shiftSolenoid = null;
	private DoubleSolenoid climbSolenoid = null;
	private DoubleSolenoid lockSolenoid = null;

	// Switches
	private DigitalInput topSwitch = null;
	private DigitalInput bottomSwitch = null;

	/**
	 * Creates the devices used in the cascade
	 */
	public Cascade() {
		// Talons
		leftFrontTalon = new SN_TalonSRX(RobotMap.CASCADE_LEFT_FRONT_TALON);
		leftBackTalon = new SN_TalonSRX(RobotMap.CASCADE_LEFT_BACK_TALON);
		rightFrontTalon = new SN_TalonSRX(RobotMap.CASCADE_RIGHT_FRONT_TALON);
		rightBackTalon = new SN_TalonSRX(RobotMap.CASCADE_RIGHT_BACK_TALON);
		leftFrontTalon.setInverted(false);
		leftBackTalon.setInverted(false);
		rightFrontTalon.setInverted(true);
		rightBackTalon.setInverted(true);

		// Encoders
		liftEncoder = new Encoder(RobotMap.CASCADE_LIFT_ENCODER_A, RobotMap.CASCADE_LIFT_ENCODER_B);

		// Solenoids
		shiftSolenoid = new DoubleSolenoid(RobotMap.CASCADE_PCM, RobotMap.CASCADE_SHIFT_SOLENOID_A,
				RobotMap.CASCADE_SHIFT_SOLENOID_B);
		climbSolenoid = new DoubleSolenoid(RobotMap.INTAKE_PCM, RobotMap.CASCADE_CLIMB_SOLENOID_A,
				RobotMap.CASCADE_CLIMB_SOLENOID_B);
		lockSolenoid = new DoubleSolenoid(RobotMap.CASCADE_PCM, RobotMap.CASCADE_LOCK_SOLENOID_A,
				RobotMap.CASCADE_LOCK_SOLENOID_B);

		// Switches
		topSwitch = new DigitalInput(RobotMap.CASCADE_TOP_SWITCH);
		bottomSwitch = new DigitalInput(RobotMap.CASCADE_BOTTOM_SWITCH);
	}

	/**
	 * @return Check if the top switch is activated. Inverted to default as false
	 */
	public boolean isTopSwitchClosed() {
		return !topSwitch.get();
	}

	/**
	 * @return Check if the bottom switch is activated. Inverted to default as false
	 */
	public boolean isBottomSwitchClosed() {
		return !bottomSwitch.get();
	}

	/**
	 * Deploy the climber pistons
	 */
	public void deployClimb() {
		climbSolenoid.set(Value.kReverse);
	}

	public boolean isClimberDeployed() {
		return climbSolenoid.get() == Value.kReverse;
	}

	/**
	 * Retract the climber pistons
	 */
	public void retractClimb() {
		climbSolenoid.set(Value.kForward);
	}

	/**
	 * Shift the gearbox to cascade
	 */
	public void shiftCascade() {
		shiftSolenoid.set(Value.kReverse);
	}

	public boolean isShiftedCascade() {
		return shiftSolenoid.get() == Value.kReverse;
	}

	/**
	 * Shift the gearbox to climb //
	 */
	public void shiftClimb() {
		shiftSolenoid.set(Value.kForward);
	}

	public boolean isShiftedClimb() {
		return shiftSolenoid.get() == Value.kForward;
	}

	/**
	 * Lock the cascade dogtooth
	 */
	public void lockCascade() {
		lockSolenoid.set(Value.kReverse);
	}

	public boolean isCascadeLocked() {
		return lockSolenoid.get() == Value.kReverse;
	}

	/**
	 * Unlock the cascade dogtooth
	 */
	public void unlockCascade() {
		lockSolenoid.set(Value.kForward);
	}

	/**
	 * @return Lift encoder distance in inches
	 */
	public double getLiftEncoderDistance() {
		return (liftEncoder.get() / RobotPreferences.CASCADE_PULSES_PER_FOOT.getValue()) * 12.0;
	}

	/**
	 * @return Default scaled lift encoder count
	 */
	public double getLiftEncoderCount() {
		return liftEncoder.get();
	}

	/**
	 * Set the lift encoder to zero
	 */
	public void resetLiftEncoder() {
		liftEncoder.reset();
	}

	/**
	 * Set the speed for the lift motors. Can not move the lift past the top or
	 * bottom switches
	 */
	public void setLiftSpeed(double speed) {
		if ((speed > 0 && isTopSwitchClosed()) || (speed < 0 && isBottomSwitchClosed()) || isCascadeLocked()) {
			speed = 0.0;
		}

		leftFrontTalon.set(speed);
		leftBackTalon.set(speed);
		rightFrontTalon.set(speed);
		rightBackTalon.set(speed);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CascadeResetEncoder());
	}
}