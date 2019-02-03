/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.Drive.*;
import frc.robot.commands.Intake.*;
import frcteam3255.robotbase.Joystick.*;
import frcteam3255.robotbase.Preferences.*;

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
    manipulatorStick.btn_3.whenPressed(new IntakeHatchDeploy());
    manipulatorStick.btn_4.whenPressed(new IntakeHatchRetract());
    manipulatorStick.btn_5.whenPressed(new IntakeHatchReach());
    manipulatorStick.btn_5.whenReleased(new IntakeHatchGrab());
    manipulatorStick.btn_11.whenPressed(new VisionDistanceRotateTest());
    manipulatorStick.btn_7.whenPressed(new DriveDistance(new SN_DoublePreference("testPID", 100.0), "testPID"));
    // manipulatorStick.btn_5.whenPressed(new DriveStraightDistance(100.0));
    // manipulatorStick.btn_6.whenPressed(new DriveRotate(90.0));
    manipulatorStick.btn_9.whenPressed(new StartMatch());
    manipulatorStick.btn_10.whenPressed(new Climb());

    // Switchboard Stick
    // switchboardStick.btn_1.whileHeld(new SetDebugMode());
    switchboardStick.btn_2.whenPressed(new VisionSetDriverMode());
    switchboardStick.btn_2.whenReleased(new VisionSetVisionMode());
  }
}