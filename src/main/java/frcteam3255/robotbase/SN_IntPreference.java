/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frcteam3255.robotbase;

import edu.wpi.first.wpilibj.Preferences;

/**
 * Add your docs here.
 */
public class SN_IntPreference extends SN_Preferences {
	private int m_defaultValue;

	public SN_IntPreference(String name, int defaultValue){
		m_name = name;
		m_defaultValue = defaultValue;
	}
	public int get(){
		if(m_useDefaults) {
			return m_defaultValue;
		}
		return Preferences.getInstance().getInt(m_name, m_defaultValue);
	}
	
}
