/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem containing NavX and field data methoods
 */
public class Navigation extends Subsystem {
	// NavX class
	public static AHRS ahrs = null;

	/**
	 * Constructs the NavX class
	 */
	public Navigation() {
		// NavX
		try {
			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error installing navX MXP: " + ex.getMessage(), true);
		}
	}

	// NavX

	/**
	 * @return Yaw of NavX
	 */
	public double getYaw() {
		return ahrs.getYaw();
	}

	/**
	 * @return Pitch of NavX
	 */
	public double getPitch() {
		return ahrs.getPitch();
	}

	/**
	 * @return Roll of NavX
	 */
	public double getRoll() {
		return ahrs.getRoll();
	}

	public double getAccelerationX() {
		return ahrs.getRawAccelX();
	}

	public double getAccelerationY() {
		return ahrs.getRawAccelY();
	}

	public double getAccelerationZ() {
		return ahrs.getRawAccelZ();
	}

	/**
	 * Reset the NavX yaw and zero it out
	 */
	public void resetYaw() {
		ahrs.reset();
	}

	/**
	 * Reset the NavX pitch
	 */
	public void resetPitch() {
		ahrs.reset();
	}

	/**
	 * @return Check if the NavX is automatically calibrating itself
	 */
	public boolean isCalibrating() {
		return ahrs.isCalibrating();
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
