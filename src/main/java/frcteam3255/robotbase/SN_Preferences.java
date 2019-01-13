package frcteam3255.robotbase;

/**
 * SuperNURDs encapsulation of RobotPreferences
 */
public abstract class SN_Preferences {
	protected static boolean m_useDefaults = true;
	protected String m_name;

	public boolean useDefaults() {
		m_useDefaults = true;
		return m_useDefaults;
	}

	public boolean useRobotPreferences(){
		m_useDefaults = false;
		return m_useDefaults;
	}

	public boolean usingDefaults() {
		return m_useDefaults;
	}
}