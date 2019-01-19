/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frcteam3255.robotbase.SN_PID;

/**
 * Add your docs here.
 */
public class VisionRotatePID extends SN_PID{

    public VisionRotatePID() {
        super("VisionRotatePID");

        setPIDPreferences(RobotPreferences.VISION_ROTATE_P, RobotPreferences.VISION_ROTATE_I, RobotPreferences.VISION_ROTATE_D);
        setMinMaxPreferences(RobotPreferences.VISION_MIN, RobotPreferences.VISION_MAX, RobotPreferences.VISION_MAX_CHANGE);
        setTargetCount(RobotPreferences.VISION_TARGET_COUNT);
    }

    @Override
    protected double returnPIDInput() {
      // Return your input value for the PID loop
      // e.g. a sensor, like a potentiometer:
      // yourPot.getAverageVoltage() / kYourMaxVoltage;
      return Robot.m_vision.getHorizontalOffset();
    }
}
