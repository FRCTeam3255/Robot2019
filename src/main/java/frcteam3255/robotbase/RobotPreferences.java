package frcteam3255.robotbase;

import edu.wpi.first.wpilibj.Preferences;

/**
 * RobotPreferences
 */
public class RobotPreferences {
    public static boolean useDefaults = true;

    public static double getDouble(String name, double defaultValue) {
        if(useDefaults) {
            return defaultValue;
        }

        return Preferences.getInstance().getDouble(name, defaultValue);
    }

	public static double encoderPulsesPerFoot() {
		return 5;
    }
    
    //PID

	public static double drivetrainP() {
		return 1.0;
	}

	public static double drivetrainI() {
		return 0;
	}

	public static double drivetrainD() {
		return 0;
	}

	public static double distancePIDMaxChange() {
		return 1.0;
	}

	public static double distancePIDMinSpeed() {
		return 0;
	}

	public static double distancePIDMaxSpeed() {
		return 1.0;
	}

	public static int drivetrainTargetCount() {
		return 100000;
	}

	public static double drivetrainTolerance() {
		return 0;
	}

	public static double timeOut() {
		return 5000000;
	}

    // TODO: Need to add similar methods for getting integers and booleans
}