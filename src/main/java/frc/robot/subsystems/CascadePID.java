/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frcteam3255.robotbase.SN_PID;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;
import frcteam3255.robotbase.Preferences.SN_IntPreference;

/**
 * PID class based off the cascade distance
 */
public class CascadePID extends SN_PID {
	// Cascade PID
	public static final SN_DoublePreference CASCADE_P = new SN_DoublePreference("cascadeP", 0.1);
	public static final SN_DoublePreference CASCADE_I = new SN_DoublePreference("cascadeI", 0.0);
	public static final SN_DoublePreference CASCADE_D = new SN_DoublePreference("cascadeD", 0.0);
	public static final SN_DoublePreference CASCADE_TOL = new SN_DoublePreference("cascadeTol", 1.0);
	public static final SN_IntPreference CASCADE_TARGET_COUNT = new SN_IntPreference("cascadeTargetCount", 1);
	public static final SN_DoublePreference CASCADE_MINOUTDOWN = new SN_DoublePreference("cascadeMinSpeedDown", 0.0);
	public static final SN_DoublePreference CASCADE_MINOUTUP = new SN_DoublePreference("cascadeMinSpeedUp", 0.33);
	public static final SN_DoublePreference CASCADE_MAXOUTDOWN = new SN_DoublePreference("cascadeMaxSpeedDown", 0.1);
	public static final SN_DoublePreference CASCADE_MAXOUTUP = new SN_DoublePreference("cascadeMaxSpeedUp", 0.5);
	public static final SN_DoublePreference CASCADE_MAXCHANGE = new SN_DoublePreference("cascadeMaxChange", 1.0);

	/**
	 * Creates a Cascade PID loop and sets PID values
	 */
	public CascadePID() {
		super();

		setPID(CASCADE_P, CASCADE_I, CASCADE_D);
		setTolerance(CASCADE_TOL);
		setTargetCount(CASCADE_TARGET_COUNT);
		setMinOutputNegative(CASCADE_MINOUTDOWN);
		setMaxOutputNegative(CASCADE_MAXOUTDOWN);
		setMaxOutputPositive(CASCADE_MAXOUTUP);
		setMaxOutputChange(CASCADE_MAXCHANGE);
	}

	/**
	 * @return Inputs the cascade encoder distance
	 */
	@Override
	protected double returnPIDInput() {
		inputValid = true;
		return Robot.m_cascade.getLiftEncoderDistance();
	}
}
