/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveRotate;
import frc.robot.commands.DriveStraightDistance;
import frc.robot.commands.IntakeDeployLeftSolenoid;
import frc.robot.commands.IntakeDeployRightSolenoid;
import frc.robot.commands.IntakeDeploySolenoid;
import frc.robot.commands.IntakeRetractLeftSolenoid;
import frc.robot.commands.IntakeRetractRightSolenoid;
import frc.robot.commands.IntakeRetractSolenoid;
import frcteam3255.robotbase.SN_DriverStick;
import frcteam3255.robotbase.SN_ManipulatorStick;

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
    public SN_DriverStick driverStick = new SN_DriverStick(0);
    public SN_ManipulatorStick manipulatorStick =  new SN_ManipulatorStick(1);
	
    public OI() {
	  manipulatorStick.btn_1.whileHeld(new IntakeDeployRightSolenoid());
      manipulatorStick.btn_1.whenReleased(new IntakeRetractRightSolenoid());
      manipulatorStick.btn_2.whileHeld(new IntakeDeployLeftSolenoid());
      manipulatorStick.btn_2.whenReleased(new IntakeRetractLeftSolenoid());
      manipulatorStick.btn_3.whileHeld(new IntakeDeploySolenoid());
      manipulatorStick.btn_3.whenReleased(new IntakeRetractSolenoid());
      manipulatorStick.btn_4.whenPressed(new DriveDistance(100.0));
      manipulatorStick.btn_5.whenPressed(new DriveStraightDistance(100.0));
      manipulatorStick.btn_6.whenPressed(new DriveRotate(90.0));

    }

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}