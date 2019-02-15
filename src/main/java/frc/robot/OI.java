/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.Cascade.CascadeMove;
import frc.robot.commands.Cascade.CascadeMoveManual;
import frc.robot.commands.Cascade.CascadeResetEncoder;
import frc.robot.commands.Drive.DriveDistanceRotateVision;
import frc.robot.commands.Intake.IntakeCargoCollectAndLift;
import frc.robot.commands.Intake.IntakeDown;
import frc.robot.commands.Intake.IntakeEject;
import frc.robot.commands.Intake.IntakeRetractHook;
import frc.robot.commands.Intake.IntakeUp;
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

	public static SN_DoublePreference VisDisSetpoint = new SN_DoublePreference("VisDisSetpoint", 24.0);
	public static SN_DoublePreference VisRotSetpoint = new SN_DoublePreference("VisRotSetpoint", 0.0);

	/**
	 * Assigns commands to buttons
	 */
	public OI() {
		// Manipulator Stick
		manipulatorStick.btn_1.whileHeld(new IntakeEject());
		manipulatorStick.btn_2.whenPressed(new IntakeCargoCollectAndLift());
		manipulatorStick.btn_3.whenPressed(new IntakeRetractHook());
		manipulatorStick.btn_4.whileHeld(new CascadeMoveManual());
		// manipulatorStick.btn_4.whileHeld(new ManualClimb());
		manipulatorStick.btn_5.whenPressed(new IntakeUp());
		manipulatorStick.btn_6.whenPressed(new IntakeDown());
		manipulatorStick.btn_7.whenPressed(new CascadeMove(RobotPreferences.HATCH_POSITION_3));
		manipulatorStick.btn_8.whenPressed(new CascadeMove(RobotPreferences.CARGO_POSITION_3));
		manipulatorStick.btn_9.whenPressed(new CascadeMove(RobotPreferences.HATCH_POSITION_2));
		manipulatorStick.btn_10.whenPressed(new CascadeMove(RobotPreferences.CARGO_POSITION_2));
		manipulatorStick.btn_11.whenPressed(new CascadeMove(RobotPreferences.HATCH_POSITION_1));
		manipulatorStick.btn_12.whenPressed(new CascadeMove(RobotPreferences.CARGO_POSITION_1));

		// Driver Stick
		// driverStick.btn_RBump slow speed
		driverStick.btn_RTrig
				.whileHeld(new DriveDistanceRotateVision(VisDisSetpoint, VisRotSetpoint, "DistanceRotateVision"));

		// Switchboard Stick
		switchboardStick.btn_2.whenPressed(new CascadeResetEncoder());
		// switchboardStick.btn_2.whenPressed(new VisionSetDriverMode());
		// switchboardStick.btn_2.whenReleased(new VisionSetVisionMode());
	}
}