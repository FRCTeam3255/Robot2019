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

    // TODO: Need to add similar methods for getting integers and booleans
}