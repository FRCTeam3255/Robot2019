package frcteam3255.robotbase;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frcteam3255.robotbase.Preferences.SN_BooleanPreference;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;
import frcteam3255.robotbase.Preferences.SN_IntPreference;

public class SN_TalonSRX extends WPI_TalonSRX {

	protected static SN_IntPreference default_peakAmps = new SN_IntPreference("SNTALON_PEAK_AMPS", 15);
	protected static SN_IntPreference default_timeAtPeak = new SN_IntPreference("SNTALON_TIME_AT_PEAK", 3000);
	protected static SN_IntPreference default_ampsLimit = new SN_IntPreference("SNTTALON_AMPS_LIMIT", 10);
	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 30;
	public static boolean kSensorPhase = true;
	public static boolean kMotorInvert = false;

	/**
	 * SuperNURDs encapsulation of the TalonSRX class
	 * <ul>
	 * <li>Resets to Factory defaults</li>
	 * <li>Disables safety</li>
	 * <li>Enables breaking</li>
	 * </ul>
	 */
	public SN_TalonSRX(int deviceNumber) {
		super(deviceNumber);
		configFactoryDefault();
		setSafetyEnabled(false);
		setNeutralMode(NeutralMode.Brake);
	}

	public SN_TalonSRX(int deviceNumber, boolean invert) {
		super(deviceNumber);
		configFactoryDefault();
		setSafetyEnabled(false);
		setNeutralMode(NeutralMode.Brake);
		if (invert) {
			this.setInverted(true);
		} else {
			this.setInverted(false);
		}
	}

	/**
	 * SuperNURDs encapsulation of the TalonSRX class
	 * <ul>
	 * <li>Resets to Factory defaults</li>
	 * <li>Disables safety</li>
	 * <li>Enables breaking</li>
	 * <li>Enables current limiting with the default values</li>
	 * </ul>
	 */
	public SN_TalonSRX(int deviceNumber, SN_BooleanPreference enableCurrentLimiting) {
		this(deviceNumber);
		setDefaultCurrentLimiting(enableCurrentLimiting);
	}

	public SN_TalonSRX(int deviceNumber, SN_TalonSRX master, boolean invert) {
		super(deviceNumber);
		configFactoryDefault();
		setSafetyEnabled(false);
		setNeutralMode(NeutralMode.Brake);
		this.follow(master);
		if (invert) {
			this.setInverted(true);
		} else {
			this.setInverted(false);
		}
	}

	/**
	 * Condigure Position PID on TalonSRX
	 * 
	 * @param encoder   : encoder type for PID to use/ encoder type hooked up to
	 *                  talon
	 * @param p         : P coefficient
	 * @param i         : i coefficient
	 * @param d         : d coefficient
	 * @param f         : f coefficient
	 * @param izone     : I Zone
	 * @param tolerance : tolerance
	 * 
	 */
	public void configurePositionPid(FeedbackDevice encoder, SN_DoublePreference p, SN_DoublePreference i,
			SN_DoublePreference d, SN_DoublePreference f, SN_IntPreference izone, SN_IntPreference tolerance) {
		this.configSelectedFeedbackSensor(encoder, kPIDLoopIdx, kTimeoutMs);

		this.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		this.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
		// this.setSensorPhase(false);

		/* Set the peak and nominal outputs */
		this.configNominalOutputForward(0, kTimeoutMs);
		this.configNominalOutputReverse(0, kTimeoutMs);
		this.configPeakOutputForward(1, kTimeoutMs);
		this.configPeakOutputReverse(-1, kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		this.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		this.config_kF(kSlotIdx, f.getValue(), kTimeoutMs);
		this.config_kP(kSlotIdx, p.getValue(), kTimeoutMs);
		this.config_kI(kSlotIdx, i.getValue(), kTimeoutMs);
		this.config_kD(kSlotIdx, d.getValue(), kTimeoutMs);

		// izone
		this.config_IntegralZone(kSlotIdx, izone.getValue());

		// Zero the sensor
		this.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);

		this.configAllowableClosedloopError(kPIDLoopIdx, tolerance.getValue(), kTimeoutMs);

	}

	/**
	 * Condigure Magic Motion PID on TalonSRX
	 * 
	 * @param encoder   : encoder type for PID to use/ encoder type hooked up to
	 *                  talon
	 * @param p         : P coefficient
	 * @param i         : i coefficient
	 * @param d         : d coefficient
	 * @param f         : f coefficient
	 * @param izone     : I Zone
	 * @param tolerance : tolerance
	 * @param cv        : cruise velocity
	 * @param accel     : accelleration
	 * 
	 */
	public void configureMMPid(FeedbackDevice encoder, SN_DoublePreference p, SN_DoublePreference i,
			SN_DoublePreference d, SN_DoublePreference f, SN_IntPreference izone, SN_IntPreference tolerance,
			SN_IntPreference cv, SN_IntPreference accel) {
		this.configSelectedFeedbackSensor(encoder, kPIDLoopIdx, kTimeoutMs);
		this.setSensorPhase(true);
		this.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		this.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

		/* Set the peak and nominal outputs */
		this.configNominalOutputForward(0, kTimeoutMs);
		this.configNominalOutputReverse(0, kTimeoutMs);
		this.configPeakOutputForward(.6, kTimeoutMs);
		this.configPeakOutputReverse(-.6, kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		this.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		this.config_kF(kSlotIdx, f.getValue(), kTimeoutMs);
		this.config_kP(kSlotIdx, p.getValue(), kTimeoutMs);
		this.config_kI(kSlotIdx, i.getValue(), kTimeoutMs);
		this.config_kD(kSlotIdx, d.getValue(), kTimeoutMs);

		// izone
		this.config_IntegralZone(kSlotIdx, izone.getValue());

		// Zero the sensor
		this.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);

		this.configAllowableClosedloopError(kPIDLoopIdx, tolerance.getValue(), kTimeoutMs);

		this.configMotionCruiseVelocity(cv.getValue(), kTimeoutMs);
		this.configMotionAcceleration(accel.getValue(), kTimeoutMs);

	}

	public void resetEncoder() {
		this.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
	}

	/**
	 * Configure Current Limiting for TalonSRXs
	 * 
	 * @param peakAmps   : Current threshold to trigger current limiting
	 * @param timeAtPeak : Duration after current exceed Peak Current to trigger
	 *                   current limiting (in miliseconds i.e. 5000ms = 5s)
	 * @param ampsLimit  : Current to mantain once current limiting has been
	 *                   triggered
	 * @param isEnabled  : Should current limiting be enabled? (This can be done
	 *                   later instead)
	 */
	public void setCurrentLimiting(SN_IntPreference peakAmps, SN_IntPreference timeAtPeak, SN_IntPreference ampsLimit,
			SN_BooleanPreference isEnabled) {
		super.configPeakCurrentLimit(peakAmps.getValue());
		super.configPeakCurrentDuration(timeAtPeak.getValue());
		super.configContinuousCurrentLimit(ampsLimit.getValue());
		super.enableCurrentLimit(isEnabled.getValue());
	}

	public void setDefaultCurrentLimiting(SN_BooleanPreference isEnabled) {
		setCurrentLimiting(default_peakAmps, default_timeAtPeak, default_ampsLimit, isEnabled);
	}
}