/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frcteam3255.robotbase.Preferences;

import edu.wpi.first.wpilibj.Preferences;

/**
 * SuperNURDs double preference base class
 */
public class SN_DoublePreference extends SN_Preferences {
	private double m_defaultValue;

	/**
	 * Creates a preference with a name and double
	 * 
	 * @param name
	 * @param defaultValue
	 */
	public SN_DoublePreference(String name, double defaultValue) {
		m_name = name;
		m_defaultValue = defaultValue;
	}

	/**
	 * @return if using defaults return code values or get new values from network
	 *         tables
	 */
	public double getValue() {
		if (isUsingDefaults()) {
			return m_defaultValue;
		}
		return Preferences.getInstance().getDouble(m_name, m_defaultValue);
	}

}
