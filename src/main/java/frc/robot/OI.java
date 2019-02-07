/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.VisionSetDriverMode;
import frc.robot.commands.VisionSetVisionMode;
import frc.robot.commands.Cascade.CascadeLift;
import frc.robot.commands.Cascade.CascadeMove;
import frc.robot.commands.Cascade.CascadeResetEncoder;
import frc.robot.commands.Climber.Climb;
import frc.robot.commands.Intake.IntakeCargoCollect;
import frc.robot.commands.Intake.IntakeCargoEject;
import frc.robot.commands.Intake.IntakeHatchDeploy;
import frc.robot.commands.Intake.IntakeHatchGrab;
import frc.robot.commands.Intake.IntakeHatchReach;
import frc.robot.commands.Intake.IntakeHatchRetract;
import frc.robot.commands.Intake.IntakePickUpHatch;
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
		manipulatorStick.btn_1.whileHeld(new IntakeCargoEject());
		manipulatorStick.btn_2.whenPressed(new IntakeCargoCollect());
		manipulatorStick.btn_3.whenPressed(new IntakePickUpHatch());
		manipulatorStick.btn_4.whenPressed(new IntakeHatchGrab());
		// manipulatorStick.btn_4.whenPressed(new ToggleIntakeHatch());
		// manipulatorStick.btn_5.whenPressed(new DeployHatch());
		manipulatorStick.btn_5.whenPressed(new IntakeHatchDeploy());
		manipulatorStick.btn_6.whenPressed(new IntakeHatchRetract());
		manipulatorStick.btn_7.whenPressed(new CascadeMove(new SN_DoublePreference("cascadeTop", 75)));
		manipulatorStick.btn_8.whenPressed(new Climb());
		// manipulatorStick.btn_8.whenPressed(new CascadeResetEncoder());
		manipulatorStick.btn_9.whenPressed(new CascadeMove(new SN_DoublePreference("cascadeMid", 50)));
		manipulatorStick.btn_11.whenPressed(new CascadeMove(new SN_DoublePreference("cascadeLow", 25)));
		manipulatorStick.btn_12.whenPressed(new CascadeMove(new SN_DoublePreference("cascadeBottom", 0.0)));

		// Driver Stick
		driverStick.btn_A.whenPressed(new CascadeMove(new SN_DoublePreference("cascadeMoveSetpoint", 100.0)));

		// Testing
		manipulatorStick.btn_10.whenPressed(
				new CascadeLift(new SN_DoublePreference("testing", RobotPreferences.TESTING_SETPOINT.getValue())));

		// Switchboard Stick
		// switchboardStick.btn_1.whileHeld(new SetDebugMode());
		switchboardStick.btn_2.whenPressed(new VisionSetDriverMode());
		switchboardStick.btn_2.whenReleased(new VisionSetVisionMode());
	}
}