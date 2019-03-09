/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class AutoPreferences {

    public static boolean doRocket() {
        return Robot.m_oi.switchboardStick.btn_1.get();
    }

    public static boolean doShipFrontFront() {
        return Robot.m_oi.switchboardStick.btn_2.get();
    }

    public static boolean doShipSide() {
        return Robot.m_oi.switchboardStick.btn_3.get();
    }

    public static String getSide() {
        if (Robot.m_oi.switchboardStick.btn_4.get()) {
            System.out.println("left side");
            return "L";
        } else {
            System.out.println("left side");
            return "R";
        }
    }

    public static String getPosition() {
        String position;

        if (Robot.m_oi.switchboardStick.btn_5.get()) {
            position = "F";
        } else if (Robot.m_oi.switchboardStick.btn_6.get()) {
            position = "M";
        } else if (Robot.m_oi.switchboardStick.btn_7.get()) {
            position = "B";
        } else {
            position = "F";
        }

        return position;
    }

    public static boolean isReset() {
        return Robot.m_oi.switchboardStick.btn_8.get();
    }
}