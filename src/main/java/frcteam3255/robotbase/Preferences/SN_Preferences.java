package frcteam3255.robotbase.Preferences;

import frc.robot.Robot;

/**
 * SuperNURDs encapsulation of RobotPreferences
 */
public abstract class SN_Preferences {
	private static boolean useDefaults = false;
	protected String m_name;

	/**
	 * Set to use coded preference values //
	 */
	// public static void useDefaults() {
	// useDefaults = true;
	// }

	/**
	 * Set not to use coded preference values
	 */
	public static void usePreferences() {
		// useDefaults = false;
		useDefaults = Robot.m_oi.switchboardStick.btn_9.get();
	}

	/**
	 * @return Check if we are using default preference vaules
	 */
	public static boolean isUsingDefaults() {
		return useDefaults;
	}
}