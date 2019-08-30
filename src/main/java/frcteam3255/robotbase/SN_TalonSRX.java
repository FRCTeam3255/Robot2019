package frcteam3255.robotbase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frcteam3255.robotbase.Preferences.SN_BooleanPreference;
import frcteam3255.robotbase.Preferences.SN_IntPreference;

public class SN_TalonSRX extends WPI_TalonSRX {

	protected static SN_IntPreference default_peakAmps = new SN_IntPreference("SNTALON_PEAK_AMPS", 15);
	protected static SN_IntPreference default_timeAtPeak = new SN_IntPreference("SNTALON_TIME_AT_PEAK", 3000);
	protected static SN_IntPreference default_ampsLimit = new SN_IntPreference("SNTTALON_AMPS_LIMIT", 10);

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

	public void setDefaultCurrentLimiting(SN_BooleanPreference isEnabled) {
		setCurrentLimiting(default_peakAmps, default_timeAtPeak, default_ampsLimit, isEnabled);
	}
}