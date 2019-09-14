package frcteam3255.robotbase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
	public static final int PID_SLOT_INDEX = 0;
	public static final int PID_LOOP_INDEX = 0;
	public static final int PID_TIMEOUT_MS = 0;
	public static boolean kMotorInvert = false;
	private int setpoint;

	/**
	 * SN wrapper for talon SRX
	 * 
	 * @param deviceNumber : Device number attached to TalonSRX through Pheonix
	 *                     Tuner tool
	 */
	public SN_TalonSRX(int deviceNumber) {
		super(deviceNumber);
		configFactoryDefault();
		setSafetyEnabled(false);
		setNeutralMode(NeutralMode.Brake);
	}

	/**
	 * SN wrapper for talon SRX
	 * 
	 * @param deviceNumber : Device number attached to TalonSRX through Pheonix
	 *                     Tuner tool
	 * @param master       : SN_TalonSRX object for this Talon to follow
	 */
	public SN_TalonSRX(int deviceNumber, SN_TalonSRX master) {
		this(deviceNumber);
		this.follow(master);
	}

	/**
	 * Enable Current Limiting
	 * 
	 * @param enableCurrentLimiting : Takes an SN_BooleanPreference
	 */
	public void enableCurrentLimiting(SN_BooleanPreference enableCurrentLimiting) {
		this.setDefaultCurrentLimiting(enableCurrentLimiting);
	}

	/**
	 * Condigure Position PID on TalonSRX
	 * 
	 * @param p         : P coefficient
	 * @param i         : i coefficient
	 * @param d         : d coefficient
	 * @param f         : f coefficient
	 * @param izone     : I Zone - zone to max out integral
	 * @param tolerance : tolerance - allowable error
	 * 
	 */
	public void setPid(SN_DoublePreference p, SN_DoublePreference i, SN_DoublePreference d, SN_DoublePreference f,
			SN_IntPreference izone, SN_IntPreference tolerance) {
		this.selectProfileSlot(PID_SLOT_INDEX, PID_LOOP_INDEX);
		this.config_kF(PID_SLOT_INDEX, f.getValue(), PID_TIMEOUT_MS);
		this.config_kP(PID_SLOT_INDEX, p.getValue(), PID_TIMEOUT_MS);
		this.config_kI(PID_SLOT_INDEX, i.getValue(), PID_TIMEOUT_MS);
		this.config_kD(PID_SLOT_INDEX, d.getValue(), PID_TIMEOUT_MS);

		this.config_IntegralZone(PID_SLOT_INDEX, izone.getValue());

		this.configAllowableClosedloopError(PID_LOOP_INDEX, tolerance.getValue(), PID_TIMEOUT_MS);
	}

	/**
	 * Condigure Position PID on TalonSRX
	 * 
	 * @param encoder   : encoder type for PID to use/ encoder type hooked up to
	 *                  talon/auxillary
	 * @param p         : P coefficient
	 * @param i         : i coefficient
	 * @param d         : d coefficient
	 * @param f         : f coefficient
	 * @param izone     : I Zone - zone to max out integral
	 * @param tolerance : tolerance - allowable error
	 * @param phase     : sensor phase - takes boolean for encoder direction
	 * 
	 */
	public void configurePositionPid(FeedbackDevice encoder, SN_DoublePreference p, SN_DoublePreference i,
			SN_DoublePreference d, SN_DoublePreference f, SN_IntPreference izone, SN_IntPreference tolerance,
			boolean phase) {
		this.configSelectedFeedbackSensor(encoder, PID_LOOP_INDEX, PID_TIMEOUT_MS);
		this.setSensorPhase(phase);

		this.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, PID_TIMEOUT_MS);

		this.selectProfileSlot(PID_SLOT_INDEX, PID_LOOP_INDEX);
		this.config_kF(PID_SLOT_INDEX, f.getValue(), PID_TIMEOUT_MS);
		this.config_kP(PID_SLOT_INDEX, p.getValue(), PID_TIMEOUT_MS);
		this.config_kI(PID_SLOT_INDEX, i.getValue(), PID_TIMEOUT_MS);
		this.config_kD(PID_SLOT_INDEX, d.getValue(), PID_TIMEOUT_MS);

		this.config_IntegralZone(PID_SLOT_INDEX, izone.getValue());

		resetEncoder();

		this.configAllowableClosedloopError(PID_LOOP_INDEX, tolerance.getValue(), PID_TIMEOUT_MS);

	}

	public void resetEncoder() {
		this.setSelectedSensorPosition(0, PID_LOOP_INDEX, PID_TIMEOUT_MS);
	}

	public void invert() {
		this.setInverted(true);
	}

	/**
	 * Set Peak/Nominal outputs
	 * 
	 * @param peakF : Peak Forward value (default 1)
	 * @param peakR : peak reverse value (default -1)
	 * @param nomF  : nominal forward value (default 0)
	 * @param nomR  : nominal reverse value (default 0)
	 */
	public void setOutputs(double peakF, double peakR, double nomF, double nomR) {
		this.configPeakOutputForward(peakF, PID_TIMEOUT_MS);
		this.configPeakOutputReverse(peakR, PID_TIMEOUT_MS);
		this.configNominalOutputForward(nomF, PID_TIMEOUT_MS);
		this.configNominalOutputReverse(nomR, PID_TIMEOUT_MS);
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
		this.configPeakCurrentLimit(peakAmps.getValue());
		this.configPeakCurrentDuration(timeAtPeak.getValue());
		this.configContinuousCurrentLimit(ampsLimit.getValue());
		this.enableCurrentLimit(isEnabled.getValue());
	}

	/**
	 * Sets current limiting with the default values
	 * 
	 * @param isEnabled : SN_BooleanPreference to enable/disable
	 */
	public void setDefaultCurrentLimiting(SN_BooleanPreference isEnabled) {
		setCurrentLimiting(default_peakAmps, default_timeAtPeak, default_ampsLimit, isEnabled);
	}

	public void setPositionSetpoint(double s) {
		// set the setpoint for the position PID
		this.set(ControlMode.Position, s);
	}

	public void setPositionMode() {
		this.set(ControlMode.Position, setpoint);
	}

	// return the error of the PID loop
	public double getError() {
		return this.getClosedLoopError();
	}

	// return the current position of the cascade
	public int getPosition() {
		return this.getSensorCollection().getQuadraturePosition();
	}

	public void setSpeedMode() {
		// set talon mode to speed mode
		// set speed to 0
		this.set(0);
	}

	public void setSpeed(double s) {
		// update the commanded speed variable

		// set the speed on the talon
		this.set(s);
	}

	// note, this routine only returns the last commanded speed when in speed mode,
	// not the commanded speed from a PID
	public double getSpeed() {
		// return the commanded speed variable
		return this.get();
	}
}
