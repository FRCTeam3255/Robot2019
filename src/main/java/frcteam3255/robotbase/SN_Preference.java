package frcteam3255.robotbase;

import edu.wpi.first.wpilibj.Preferences;

/**
 * SuperNURDs encapsulation of RobotPreferences
 */
public class SN_Preference {
	public static boolean useDefaults = true;
	
	private String m_name;
	private double m_defaultDoubleValue = 0;
	private boolean m_defaultBooleanValue = false;

	public SN_Preference(String name, double defaultValue){
		m_name = name;
		m_defaultDoubleValue = defaultValue;
	}

	public SN_Preference(String name, boolean defaultValue){
		m_name = name;
		m_defaultBooleanValue = defaultValue;
	}

    public double getDouble() {
        if(useDefaults) {
            return m_defaultDoubleValue;
        }
        return Preferences.getInstance().getDouble(m_name, m_defaultDoubleValue);
	}
	
    public boolean getBoolean() {
        if(useDefaults) {
            return m_defaultBooleanValue;
        }
        return Preferences.getInstance().getBoolean(m_name, m_defaultBooleanValue);
    }
}