/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frcteam3255.robotbase;

import frcteam3255.robotbase.Preferences.SN_BooleanPreference;

/**
 * Add your docs here.
 */
public class SN_Debug {

	boolean disableMessages = false;
	String m_name;

	public SN_Debug(String name) {
		disableMessages = true;
	}

	/**
	 * @param printMessages print yes or no
	 */
	public void disableMessages(SN_BooleanPreference disable) {
		this.disableMessages = disable.getValue();
	}

	public void printDebug(String message, Object value) {
		if (!disableMessages) {
			System.out.println(m_name + ": " + message + value);
		}
	}
}
