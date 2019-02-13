/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.VisionSetDriverMode;
import frc.robot.commands.VisionSetVisionMode;
import frc.robot.commands.Cascade.CascadeMove;
import frc.robot.commands.Cascade.CascadeMoveManual;
import frc.robot.commands.Drive.DriveDistanceRotateVision;
import frc.robot.commands.Drive.DriveDistanceVision;
import frc.robot.commands.Intake.IntakeCargoCollect;
import frc.robot.commands.Intake.IntakeDown;
import frc.robot.commands.Intake.IntakeEject;
import frc.robot.commands.Intake.IntakeHatchGrab;
import frc.robot.commands.Intake.IntakeUp;
import frcteam3255.robotbase.Joystick.SN_DualActionStick;
import frcteam3255.robotbase.Joystick.SN_Extreme3DStick;
import frcteam3255.robotbase.Joystick.SN_SwitchboardStick;
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
	public SN_SwitchboardStick switchboardStick = new SN_SwitchboardStick(2);

	/**
	 * Assigns commands to buttons
	 */
	public OI() {
		// Manipulator Stick
		manipulatorStick.btn_1.whileHeld(new IntakeEject());
		manipulatorStick.btn_2.whenPressed(new IntakeCargoCollect());
		manipulatorStick.btn_3.whenPressed(new IntakeHatchGrab());
		manipulatorStick.btn_4.whileHeld(new CascadeMoveManual());
		// manipulatorStick.btn_4.whenPressed(new ToggleIntakeHatch());
		manipulatorStick.btn_5.whenPressed(new IntakeUp());
		manipulatorStick.btn_6.whenPressed(new IntakeDown());
		manipulatorStick.btn_7.whenPressed(new CascadeMove(new SN_DoublePreference("hatchPos3", 51)));
		manipulatorStick.btn_8.whenPressed(new CascadeMove(new SN_DoublePreference("cargoPos3", 61)));
		manipulatorStick.btn_9.whenPressed(new CascadeMove(new SN_DoublePreference("hatchPos2", 28)));
		manipulatorStick.btn_10.whileHeld(new CascadeMove(new SN_DoublePreference("cargoPos2", 43)));
		manipulatorStick.btn_11.whenPressed(new CascadeMove(new SN_DoublePreference("hatchPos1", 5)));
		manipulatorStick.btn_12.whenPressed(new CascadeMove(new SN_DoublePreference("cargoPos1", 20.0)));

		// Driver Stick
		driverStick.btn_Y
				.whenPressed(new DriveDistanceVision(new SN_DoublePreference("DriveDistanceVisionsetPoint", 100.00)));

		driverStick.btn_RTrig.whileHeld(new DriveDistanceRotateVision((new SN_DoublePreference("VisDisSetpoint", 24.0)),
				(new SN_DoublePreference("VisRotSetpoint", 0.0)), "DistanceRotateVision"));

		// Switchboard Stick
		// switchboardStick.btn_1.whileHeld(new SetDebugMode());
		switchboardStick.btn_2.whenPressed(new VisionSetDriverMode());
		switchboardStick.btn_2.whenReleased(new VisionSetVisionMode());
	}
}