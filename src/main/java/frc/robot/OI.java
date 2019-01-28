/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.Climb;
import frc.robot.commands.StartMatch;
import frc.robot.commands.VisionDistanceRotateTest;
import frc.robot.commands.Drive.DriveDistance;
import frc.robot.commands.Intake.IntakeCargoCollect;
import frc.robot.commands.Intake.IntakeCargoEject;
import frc.robot.commands.Intake.IntakeHatchDeploy;
import frc.robot.commands.Intake.IntakeHatchEject;
import frc.robot.commands.Intake.IntakeHatchReload;
import frc.robot.commands.Intake.IntakeHatchRetract;
import frcteam3255.robotbase.Joystick.SN_DriverStick;
import frcteam3255.robotbase.Joystick.SN_ManipulatorStick;
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
  public SN_DriverStick driverStick = new SN_DriverStick(0);
  public SN_ManipulatorStick manipulatorStick = new SN_ManipulatorStick(1);
  // public SN_SwitchboardStick switchboardStick = new SN_SwitchboardStick(2);

  public OI() {
    // Manipulator Stick
    manipulatorStick.btn_1.whileHeld(new IntakeCargoEject());
    manipulatorStick.btn_2.whenPressed(new IntakeCargoCollect());
    manipulatorStick.btn_3.whenPressed(new IntakeHatchDeploy());
    manipulatorStick.btn_4.whenPressed(new IntakeHatchRetract());
    manipulatorStick.btn_5.whenPressed(new IntakeHatchEject());
    manipulatorStick.btn_5.whenReleased(new IntakeHatchReload());
    manipulatorStick.btn_11.whenPressed(new VisionDistanceRotateTest());
    manipulatorStick.btn_7.whenPressed(new DriveDistance(new SN_DoublePreference("testPID", 100.0), "testPID"));
    // manipulatorStick.btn_5.whenPressed(new DriveStraightDistance(100.0));
    // manipulatorStick.btn_6.whenPressed(new DriveRotate(90.0));
    manipulatorStick.btn_9.whenPressed(new StartMatch());
    manipulatorStick.btn_10.whenPressed(new Climb());

    // Switchboard Stick
    // switchboardStick.btn_1.whileHeld(new SetDebugMode());
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