/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frcteam3255.robotbase.Preferences.SN_Preferences;

/**
 * Sets debug mode for using preferences from network tables
 */
public class SetDebugMode extends Command {
  public SetDebugMode() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Use network tables values
    SN_Preferences.usePreferences();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    // Go back to using coded values
    SN_Preferences.useDefaults();
  }

  @Override
  protected void interrupted() {
    end();
  }
}