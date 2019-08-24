/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frcteam3255.robotbase.SN_TalonSRX;
import frcteam3255.robotbase.Preferences.SN_BooleanPreference;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;
import frcteam3255.robotbase.Preferences.SN_IntPreference;

/**
 * Subsytem containing the cascade devices and methoods
 */
public class Cascade extends Subsystem {
	private double liftSpeed = 0.0;

	// Talons
	private SN_TalonSRX leftFrontTalon = null;
	private SN_TalonSRX leftBackTalon = null;
	private SN_TalonSRX rightFrontTalon = null;
	private SN_TalonSRX rightBackTalon = null;

	// Encoders
	// private Encoder liftEncoder = null;

	// Solenoids
	private DoubleSolenoid shiftSolenoid = null;
	private DoubleSolenoid lockSolenoid = null;

	// Switches
	private DigitalInput topSwitch = null;
	private DigitalInput bottomSwitch = null;
	private DigitalInput topClimbSwitch = null;
	private DigitalInput bottomClimbSwitch = null;

	/** Current threshold to trigger current limit */
	private static final SN_IntPreference PEAK_AMPS = new SN_IntPreference("drivePeakAmps", 17);
	/**
	 * Duration (in miliseconds i.e. 5000ms = 5s) after current exceed Peak Current
	 * to trigger current limit
	 */
	private static final SN_IntPreference PEAK_TIME = new SN_IntPreference("drivePeakTimeMs", 5000);
	/** Current to mantain once current limit has been triggered */
	private static final SN_IntPreference LIMIT_AMPS = new SN_IntPreference("driveLimitAmps", 6);
	/** Set if current is limited */
	private static final SN_BooleanPreference ENABLE_CURRENT_LIMITING = new SN_BooleanPreference(
			"driveEnableCurrentLimit", false);

	// Set the directions of the shift solenoid
	private static final Value cascadeValue = Value.kReverse;
	private static final Value climbValue = Value.kForward;

	// Set the directions of the shift solenoid
	private static final Value lockValue = Value.kReverse;
	private static final Value unlockValue = Value.kForward;

	/**
	 * Creates the devices used in the cascade
	 */
	public Cascade() {

		// Initialize Talons
		leftFrontTalon = new SN_TalonSRX(RobotMap.CASCADE_LEFT_FRONT_TALON,
				RobotPreferences.CASCADE_MAXOUTUP.getValue(), RobotPreferences.CASCADE_MAXOUTDOWN.getValue(),
				RobotPreferences.CASCADE_MINOUTUP.getValue(), RobotPreferences.CASCADE_MINOUTDOWN.getValue());
		leftBackTalon = new SN_TalonSRX(RobotMap.CASCADE_LEFT_BACK_TALON, leftFrontTalon, false,
				RobotPreferences.CASCADE_MAXOUTUP.getValue(), RobotPreferences.CASCADE_MAXOUTDOWN.getValue(),
				RobotPreferences.CASCADE_MINOUTUP.getValue(), RobotPreferences.CASCADE_MINOUTDOWN.getValue());
		rightFrontTalon = new SN_TalonSRX(RobotMap.CASCADE_RIGHT_FRONT_TALON, leftFrontTalon, true,
				RobotPreferences.CASCADE_MAXOUTUP.getValue(), RobotPreferences.CASCADE_MAXOUTDOWN.getValue(),
				RobotPreferences.CASCADE_MINOUTUP.getValue(), RobotPreferences.CASCADE_MINOUTDOWN.getValue());
		rightBackTalon = new SN_TalonSRX(RobotMap.CASCADE_RIGHT_BACK_TALON, leftFrontTalon, true,
				RobotPreferences.CASCADE_MAXOUTUP.getValue(), RobotPreferences.CASCADE_MAXOUTDOWN.getValue(),
				RobotPreferences.CASCADE_MINOUTUP.getValue(), RobotPreferences.CASCADE_MINOUTDOWN.getValue());

		// Configure Position Pid
		leftFrontTalon.configurePositionPid(FeedbackDevice.QuadEncoder, RobotPreferences.p, RobotPreferences.i,
				RobotPreferences.d, RobotPreferences.f, RobotPreferences.iz, RobotPreferences.tol, false);

		// leftFrontTalon.selectProfileSlot(0, 0);
		// leftFrontTalon.config_kF(0, RobotPreferences.f.getValue());
		// leftFrontTalon.config_kP(0, RobotPreferences.p.getValue());
		// leftFrontTalon.config_kI(0, RobotPreferences.i.getValue());
		// leftFrontTalon.config_kD(0, RobotPreferences.d.getValue());
		// Current Limiting Assignment
		leftFrontTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		leftBackTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		rightFrontTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);
		rightBackTalon.setCurrentLimiting(PEAK_AMPS, PEAK_TIME, LIMIT_AMPS, ENABLE_CURRENT_LIMITING);

		// Solenoids
		shiftSolenoid = new DoubleSolenoid(RobotMap.CASCADE_PCM, RobotMap.CASCADE_SHIFT_SOLENOID_A,
				RobotMap.CASCADE_SHIFT_SOLENOID_B);

		lockSolenoid = new DoubleSolenoid(RobotMap.CASCADE_PCM, RobotMap.CASCADE_LOCK_SOLENOID_A,
				RobotMap.CASCADE_LOCK_SOLENOID_B);

		unlockCascade();
		shiftCascade();

		// Switches
		topSwitch = new DigitalInput(RobotMap.CASCADE_TOP_SWITCH);
		bottomSwitch = new DigitalInput(RobotMap.CASCADE_BOTTOM_SWITCH);
		topClimbSwitch = new DigitalInput(RobotMap.CASCADE_TOP_CLIMB_SWITCH);
		bottomClimbSwitch = new DigitalInput(RobotMap.CASCADE_BOTTOM_CLIMB_SWITCH);
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
	 * @return Check if the top climb switch is activated. Inverted to default as
	 *         false
	 */
	public boolean isTopClimbSwitchClosed() {
		return !topClimbSwitch.get();
	}

	/*
	 * @return Check if the climb bottom switch is activated. Inverted to default as
	 * false
	 */
	public boolean isBottomClimbSwitchClosed() {
		return !bottomClimbSwitch.get();
	}

	/**
	 * Shift the gearbox to cascade
	 */
	public void shiftCascade() {
		shiftSolenoid.set(cascadeValue);
	}

	public boolean isShiftedCascade() {
		return shiftSolenoid.get() == cascadeValue;
	}

	/**
	 * Shift the gearbox to climb
	 */
	public void shiftClimb() {
		shiftSolenoid.set(climbValue);
	}

	public boolean isShiftedClimb() {
		return shiftSolenoid.get() == climbValue;
	}

	public String getPIDValues() {
		return " ";
	}

	/**
	 * @return Default scaled lift encoder count
	 */
	public double getLiftEncoderCount() {
		return (double) leftFrontTalon.getSensorCollection().getQuadraturePosition();
	}

	/**
	 * @return Lift encoder distance in inches
	 */
	public double getLiftEncoderDistance() {
		return (getLiftEncoderCount() / RobotPreferences.CASCADE_PULSES_PER_FOOT.getValue()) * 12.0;
	}

	/**
	 * Lock the cascade dogtooth
	 */
	public void lockCascade() {
		lockSolenoid.set(lockValue);
	}

	public boolean isCascadeLocked() {
		return lockSolenoid.get() == lockValue;
	}

	/**
	 * Unlock the cascade dogtooth
	 */
	public void unlockCascade() {
		lockSolenoid.set(unlockValue);
	}

	public boolean isCascadeUnlocked() {
		return lockSolenoid.get() == unlockValue;
	}

	/**
	 * Set the lift encoder to zero
	 */
	public void resetLiftEncoder() {
		leftFrontTalon.resetEncoder();
	}

	/**
	 * Set the speed for the lift motors. Can not move the lift past the top or
	 * bottom switches. Reset the lift encoder at bottom.
	 */
	public void setLiftSpeed(double speed) {
		liftSpeed = speed;
		// unlockCascade();
		if (isShiftedCascade()) {
			if (isBottomSwitchClosed()) {
				System.out.println("BottomClosed");
				resetLiftEncoder();
			}

			if ((speed > 0 && isTopSwitchClosed()) || (speed < 0 && isBottomSwitchClosed())) {
				System.out.println("topclosed");
				speed = 0.0;
			}
		} else {
			if ((speed > 0 && isTopClimbSwitchClosed()) || (speed < 0 && isBottomClimbSwitchClosed())) {
				System.out.println("climbclosed");
				speed = 0.0;
			}
		}

		leftFrontTalon.set(speed);
		leftBackTalon.set(speed);
		rightFrontTalon.set(speed);
		rightBackTalon.set(speed);

	}

	// TODO: examine all uses
	public void stopMotors() {
		leftFrontTalon.set(0);
		leftBackTalon.set(0);
		rightFrontTalon.set(0);
		rightBackTalon.set(0);

	}

	public void talonPid(double inputPosition) {

		double position = inputPosition;

		if (isShiftedCascade()) {
			if (isBottomSwitchClosed() && position < 0) {
				System.out.println("BottomClosed");
				position = 0;
				resetLiftEncoder();
			}
		}
		leftFrontTalon.set(ControlMode.Position, position);

		leftBackTalon.follow(leftFrontTalon);
		rightFrontTalon.follow(leftFrontTalon);
		rightBackTalon.follow(leftFrontTalon);
		leftFrontTalon.setInverted(false);
		leftBackTalon.setInverted(InvertType.FollowMaster);
		rightFrontTalon.setInverted(InvertType.OpposeMaster);
		rightBackTalon.setInverted(InvertType.OpposeMaster);
	}

	public int talonPidError() {
		return leftFrontTalon.getClosedLoopError();
	}

	public boolean talonPidEnd() {
		return false;
	}

	public double getLiftSpeed() {
		return liftSpeed;
	}

	@Override
	public void initDefaultCommand() {

	}
}