/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.Cascade.*;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Vision.VisionDriveDistanceRotate;
import frc.robot.commands.Vision.VisionToggleMode;
import frc.robot.subsystems.Intake.fieldHeights;
import frcteam3255.robotbase.Joystick.SN_DualActionStick;
import frcteam3255.robotbase.Joystick.SN_Extreme3DStick;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public SN_DualActionStick driverStick = new SN_DualActionStick(0);
	public SN_Extreme3DStick manipulatorStick = new SN_Extreme3DStick(1);
	public SN_Extreme3DStick switchboardStick = new SN_Extreme3DStick(2);

	public static SN_DoublePreference VisDisSetpoint = new SN_DoublePreference("VisDisSetpoint", 10.0);
	public static SN_DoublePreference VisRotSetpoint = new SN_DoublePreference("VisRotSetpoint", 0.2);

	/**
	 * Assigns commands to buttons
	 */
	public OI() {
		// Manipulator Stick
		manipulatorStick.btn_1.whileHeld(new IntakeHatchCargoEject());
		manipulatorStick.btn_2.whenPressed(new IntakeCargoCollectGroup());
		manipulatorStick.btn_3.whenPressed(new IntakeHookToggle());
		manipulatorStick.btn_4.whileHeld(new CascadeManualGroup());
		manipulatorStick.btn_5.whenPressed(new IntakeRetractGroup());
		manipulatorStick.btn_6.whenPressed(new IntakeDeployGroup());
		manipulatorStick.btn_7.whenPressed(new ClimbDeploy());
		manipulatorStick.btn_8.whenPressed(new CascadePositionGroup(fieldHeights.HIGH));
		manipulatorStick.btn_9.whileHeld(new ClimbManual());
		manipulatorStick.btn_10.whenPressed(new CascadePositionGroup(fieldHeights.MED));
		manipulatorStick.btn_11.whenPressed(new CascadePosition(fieldHeights.CSHIP));
		manipulatorStick.btn_12.whenPressed(new CascadePositionGroup(fieldHeights.LOW));

		// Driver Stick
		driverStick.btn_A.whileHeld(new IntakeFeederGroup());
		driverStick.btn_B.whileHeld(new IntakePlaceHatchGroup());
		// driverStick.btn_RBump slow speed
		driverStick.btn_LTrig.whenPressed(new VisionToggleMode());
		driverStick.btn_RTrig
				.whileHeld(new VisionDriveDistanceRotate(VisDisSetpoint, VisRotSetpoint, "DistanceRotateVision"));
		driverStick.btn_Start.whenPressed(new CascadeStartMatch());
	}
}