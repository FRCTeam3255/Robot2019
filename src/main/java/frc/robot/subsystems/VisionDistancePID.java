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
public class VisionDistancePID extends SN_PID{

    public VisionDistancePID() {
        super("VisionDistancePID");

        setPIDPreferences(RobotPreferences.VISION_DISTANCE_P, RobotPreferences.VISION_DISTANCE_I, RobotPreferences.VISION_DISTANCE_D);
        setMinMaxPreferences(RobotPreferences.VISION_MIN, RobotPreferences.VISION_MAX, RobotPreferences.VISION_MAX_CHANGE);
        setTargetCount(RobotPreferences.VISION_TARGET_COUNT);
    }

    @Override
    protected double returnPIDInput() {
      // Return your input value for the PID loop
      // e.g. a sensor, like a potentiometer:
      // yourPot.getAverageVoltage() / kYourMaxVoltage;
      return Robot.m_vision.getDistance();
    }
}
