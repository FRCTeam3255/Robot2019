/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frcteam3255.robotbase.Joystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Custom 12 Button Joystick set up for Logitech F310 Gamepad
 * <p> Adds custom axis return methods such as {@link #getArcadeMove()} & {@link #getArcadeRotate()}
 */
public class SN_DriverStick extends Joystick{
	/** Joystick Button 1 */
	public Button btn_X = new JoystickButton(this, 1);
	/** Joystick Button 2 */
	public Button btn_A = new JoystickButton(this, 2);
	/** Joystick Button 3 */
	public Button btn_B = new JoystickButton(this, 3);
	/** Joystick Button 4 */
	public Button btn_Y = new JoystickButton(this, 4);
	/** Joystick Button 5 */
	public Button btn_LBump = new JoystickButton(this, 5);
	/** Joystick Button 6 */
	public Button btn_RBump = new JoystickButton(this, 6);
	/** Joystick Button 7 */
	public Button btn_LTrig = new JoystickButton(this, 7);
	/** Joystick Button 8 */
	public Button btn_RTrig = new JoystickButton(this, 8);
	/** Joystick Button 9 */
	public Button btn_Back = new JoystickButton(this, 9);
	/** Joystick Button 10 */
	public Button btn_Start = new JoystickButton(this, 10);
	/** Joystick Button 11 */
	public Button btn_LStick = new JoystickButton(this, 11);
	/** Joystick Button 12 */
	public Button btn_RStick = new JoystickButton(this, 12);

	// Axes
	private static final int AXIS_ARCADE_MOVE = 1;
	private static final int AXIS_ARCADE_ROTATE = 2;
	private static final int AXIS_ARCADE_STRAFE = 0;

	private static final int AXIS_TANK_LEFT = 1;
	private static final int AXIS_TANK_RIGHT = 5;

	/**
	 * Logitech F310 Gamepad with 12 Buttons and custom Axes
	 *
	 * @param port The port on the Driver Station that the joystick is plugged into.
	 */
	public SN_DriverStick(final int port){
		super(port);
	}

	// Arcade Drive
	/**
	 * @return inverted position value of RawAxis({@value #AXIS_ARCADE_MOVE})
	 */
	public double getArcadeMove(){
		return -getRawAxis(AXIS_ARCADE_MOVE);
	}

	/**
	 * @return position value of RawAxis({@value #AXIS_ARCADE_ROTATE})
	 */
	public double getArcadeRotate(){
		return getRawAxis(AXIS_ARCADE_ROTATE);
	}

	/**
	 * @return inverted position value of RawAxis({@value #AXIS_ARCADE_STRAFE})
	 */
	public double getArcadeStrafe(){
		return getRawAxis(AXIS_ARCADE_STRAFE);
	}

	// Tank Drive
	/**
	 * @return inverted position value of RawAxis({@value #AXIS_TANK_LEFT})
	 */
	public double getTankLeft(){
		return -getRawAxis(AXIS_TANK_LEFT);
	}
	
	/**
	 * @return inverted position value of RawAxis({@value #AXIS_TANK_RIGHT})
	 */
	public double getTankRight(){
		return -getRawAxis(AXIS_TANK_RIGHT);
	}
}
