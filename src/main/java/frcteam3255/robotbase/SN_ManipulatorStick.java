/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frcteam3255.robotbase;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * Custom 12 Button Joystick set up for Logitech Extreme 3D Joystick
 */
public class SN_ManipulatorStick extends Joystick {
	public Button btn_1 = new JoystickButton(this, 1);
    public Button btn_2 = new JoystickButton(this, 2);
    public Button btn_3 = new JoystickButton(this, 3);
    public Button btn_4 = new JoystickButton(this, 4);
    public Button btn_5 = new JoystickButton(this, 5);
    public Button btn_6 = new JoystickButton(this, 6);
    public Button btn_7 = new JoystickButton(this, 7);
    public Button btn_8 = new JoystickButton(this, 8);
    public Button btn_9 = new JoystickButton(this, 9);
    public Button btn_10 = new JoystickButton(this, 10);
    public Button btn_11 = new JoystickButton(this, 11);
	public Button btn_12 = new JoystickButton(this, 12);
	
	private static final int AXIS_Y = 1;
	
	/**
	 * Logitech Extreme 3D Pro Joystick with 12 buttons
	 *
	 * @param port The port on the Driver Station that the joystick is plugged into.
	 */
	public SN_ManipulatorStick(final int port){
		super(port);
	}

	/**
	 * @return inverted position value of RawAxis({@value #AXIS_Y})
	 */
	public double getYAxis(){
		return -getRawAxis(AXIS_Y);
	}
}
