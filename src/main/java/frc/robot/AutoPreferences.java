/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frcteam3255.robotbase.Joystick.SN_SwitchboardStick;
import frcteam3255.robotbase.Preferences.SN_Preferences;

/**
 * Add your docs here.
 */
public class AutoPreferences {

    public static SN_SwitchboardStick switchboardStick = new SN_SwitchboardStick(2);

    public static boolean doRocket() {
        return switchboardStick.btn_10.get();
    }

    public static boolean doShipFrontFront() {
        return switchboardStick.btn_8.get();
    }

    public static boolean doShipSide() {
        return switchboardStick.btn_6.get();
    }

    public static String getSide() {
        if (switchboardStick.btn_4.get()) {
            return "L";
        } else {
            return "R";
        }
    }

    public static String getPosition() {
        String position;

        if (switchboardStick.btn_9.get()) {
            position = "F";
        } else if (switchboardStick.btn_7.get()) {
            position = "M";
        } else if (switchboardStick.btn_5.get()) {
            position = "B";
        } else {
            position = "F";
        }

        return position;
    }

    public static void setDebug() {
        if (switchboardStick.btn_2.get()) {
            SN_Preferences.usePreferences();
        } else {
            SN_Preferences.useDefaults();
        }
    }

    public static boolean isReset() {
        return switchboardStick.btn_1.get();
    }
}