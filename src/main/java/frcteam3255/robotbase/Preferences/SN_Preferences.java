package frcteam3255.robotbase.Preferences;

/**
 * SuperNURDs encapsulation of RobotPreferences
 */
public abstract class SN_Preferences {
	private static boolean useDefaults = false;
	protected String m_name;

	/**
	 * Set to use preference values in code
	 */
	public static void useDefaults() {
		useDefaults = true;
	}

	/**
	 * Set not to use preference values in code
	 */
	public static void usePreferences() {
		useDefaults = false;
	}

	/**
	 * @return Check if we are using default preference vaules
	 */
	public static boolean isUsingDefaults() {
		return useDefaults;
	}
}