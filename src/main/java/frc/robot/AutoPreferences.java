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

    public static boolean isDebug() {
        return Robot.m_oi.switchboardStick.btn_1.get();
    }

    public static boolean doRocket() {
        return Robot.m_oi.switchboardStick.btn_2.get();
    }

    public static boolean doShipFrontFront() {
        return Robot.m_oi.switchboardStick.btn_3.get();
    }

    public static boolean doShipSideFront() {
        return Robot.m_oi.switchboardStick.btn_4.get();
    }

    public static boolean doShipSideMid() {
        return Robot.m_oi.switchboardStick.btn_5.get();
    }

    public static boolean doShipSideBack() {
        return Robot.m_oi.switchboardStick.btn_6.get();
    }

    public static String getSide() {
        String side;

        if (Robot.m_oi.switchboardStick.btn_7.get()) {
            side = "L";
        } else if (Robot.m_oi.switchboardStick.btn_8.get()) {
            side = "R";
        } else {
            side = "R";
        }

        return side;
    }

    public static boolean isReset() {
        return Robot.m_oi.switchboardStick.btn_9.get();
    }

}
