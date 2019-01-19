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
 * Custom 32 Button Joystick set up for the custom-made SuperNURDs Switchboard
 */
public class SN_SwitchboardStick extends Joystick {
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
	public Button btn_13 = new JoystickButton(this, 13);
	public Button btn_14 = new JoystickButton(this, 14);
	public Button btn_15 = new JoystickButton(this, 15);
	public Button btn_16 = new JoystickButton(this, 16);
	public Button btn_17 = new JoystickButton(this, 17);
	public Button btn_18 = new JoystickButton(this, 18);
	public Button btn_19 = new JoystickButton(this, 19);
	public Button btn_20 = new JoystickButton(this, 20);
	public Button btn_21 = new JoystickButton(this, 21);
    public Button btn_22 = new JoystickButton(this, 22);
    public Button btn_23 = new JoystickButton(this, 23);
    public Button btn_24 = new JoystickButton(this, 24);
    public Button btn_25 = new JoystickButton(this, 25);
    public Button btn_26 = new JoystickButton(this, 26);
    public Button btn_27 = new JoystickButton(this, 27);
    public Button btn_28 = new JoystickButton(this, 28);
    public Button btn_29 = new JoystickButton(this, 29);
	public Button btn_30 = new JoystickButton(this, 30);
	public Button btn_31 = new JoystickButton(this, 31);
    public Button btn_32 = new JoystickButton(this, 32);
	
	/**
	 * Logitech Extreme 3D Pro Joystick with 12 buttons
	 *
	 * @param port The port on the Driver Station that the joystick is plugged into.
	 */
	public SN_SwitchboardStick(final int port){
		super(port);
	}
}