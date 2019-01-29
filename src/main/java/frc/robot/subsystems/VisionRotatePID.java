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
 * PID class based on the horizontal offset of vision targets
 */
public class VisionRotatePID extends SN_PID {

    /**
     * Creates a VisionRotatePID and sets PID values
     */
    public VisionRotatePID() {
        super();

        setPID(RobotPreferences.VISION_ROTATE_P, RobotPreferences.VISION_ROTATE_I, RobotPreferences.VISION_ROTATE_D);
    }

    /**
     * @return Inputs the horizontal offset into PID when there is a target
     */
    @Override
    protected double returnPIDInput() {
        double offset = 0;

        if (Robot.m_vision.targetFound()) {
            offset = Robot.m_vision.getHorizontalOffset();
            this.inputValid = true;
        } else {
            this.inputValid = false;
        }

        return offset;
    }
}
