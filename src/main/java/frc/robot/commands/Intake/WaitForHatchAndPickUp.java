/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frc.robot.commands.DoDelay;
import frc.robot.commands.Cascade.CascadeMove;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class WaitForHatchAndPickUp extends CommandGroup {
  /**
   * Add your docs here.
   */
  public WaitForHatchAndPickUp() {
    requires(Robot.m_cascade);
    requires(Robot.m_intake);

    addSequential(new IntakeWaitForHatch());
    addSequential(new IntakeRetractHook());
    addSequential(new DoDelay(new SN_DoublePreference("hatchPickUpDelay", 0.5)));
    addSequential(new IntakeRetract());
    addSequential(new CascadeMove(RobotPreferences.HATCH_POSITION_1));
  }
}
