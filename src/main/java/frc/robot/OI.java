/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.Cascade.*;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Drive.*;
import frc.robot.commands.Intake.*;
import frcteam3255.robotbase.Joystick.SN_DualActionStick;
import frcteam3255.robotbase.Joystick.SN_Extreme3DStick;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
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
		manipulatorStick.btn_1.whileHeld(new HatchCargoEject());
		manipulatorStick.btn_2.whenPressed(new IntakeCargoCollectGroup());
		manipulatorStick.btn_3.whenPressed(new IntakeHookToggle());
		manipulatorStick.btn_4.whileHeld(new CascadeManualGroup());
		manipulatorStick.btn_5.whenPressed(new IntakeRetractGroup());
		manipulatorStick.btn_6.whenPressed(new IntakeDeployGroup());
		// manipulatorStick.btn_7
		manipulatorStick.btn_8.whenPressed(new CascadePositionGroup(3));
		manipulatorStick.btn_9.whileHeld(new ClimbManual());
		manipulatorStick.btn_10.whenPressed(new CascadePositionGroup(2));
		// manipulatorStick.btn_11
		manipulatorStick.btn_12.whenPressed(new CascadePositionGroup(1));

		// Driver Stick
		driverStick.btn_A.whenPressed(new IntakeFeederGroup());
		driverStick.btn_B.whenPressed(new PlaceHatchGroup());
		// driverStick.btn_RBump slow speed
		driverStick.btn_RTrig
				.whileHeld(new DriveDistanceRotateVision(VisDisSetpoint, VisRotSetpoint, "DistanceRotateVision"));

		// Switchboard Stick
		switchboardStick.btn_2.whenPressed(new CascadeResetEncoder());
		// switchboardStick.btn_2.whenPressed(new VisionSetDriverMode());
		// switchboardStick.btn_2.whenReleased(new VisionSetVisionMode());
	}
}