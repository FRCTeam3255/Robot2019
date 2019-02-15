/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotPreferences;
import frc.robot.commands.DoDelay;
import frc.robot.commands.Cascade.CascadeBottom;
import frc.robot.commands.Cascade.CascadeMove;
import frc.robot.commands.Cascade.CascadeUnweight;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class IntakeCargoCollectAndLift extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeCargoCollectAndLift() {
    addSequential(new CascadeUnweight());
    addSequential(new CascadeBottom());
    addSequential(new IntakeDeploy());
    addSequential(new IntakeCargoCollect());
    addSequential(new DoDelay(new SN_DoublePreference("cargoCollectAndLift", 0.5)));
    addSequential(new CascadeMove(RobotPreferences.CARGO_POSITION_1));
  }
}