package frcteam3255.robotbase;

/**
 * SuperNURDs encapsulation of RobotPreferences
 */
public abstract class SN_Preferences {
	private static boolean useDefaults = true;
	protected String m_name;

	public static void useDefaults() {
		useDefaults = true;
	}

	public static void usePreferences() {
		useDefaults = false;
	}

	public static boolean isUsingDefaults() {
		return useDefaults;
	}
}