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
 * PID class based off the distance from vision targets
 */
public class VisionDistancePID extends SN_PID {

    /**
     * Creates a VisionDistancePID loop and sets PID values
     */
    public VisionDistancePID() {
        super();

        setPID(RobotPreferences.VISION_DISTANCE_P, RobotPreferences.VISION_DISTANCE_I,
                RobotPreferences.VISION_DISTANCE_D);
    }

    /**
     * @return Inputs the distance from the vision target when it is in front of the
     *         camera
     */
    @Override
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        double d = Robot.m_vision.getDistance();

        if (d < 0) {
            inputValid = false;
            return -1;
        }
        inputValid = true;
        return d;
    }
}
