/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoDelay;
import frc.robot.commands.Cascade.CascadeMove;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class IntakeUp extends CommandGroup {

  private static SN_DoublePreference hatchPos1 = new SN_DoublePreference("hatchPosition1", 1.0);

  /**
   * Add your docs here.
   */
  public IntakeUp() {
    addSequential(new IntakeRetractHook());
    addSequential(new DoDelay(new SN_DoublePreference("IntakeUpDelay1", 0.5)));
    addSequential(new IntakeVertical());
    addSequential(new DoDelay(new SN_DoublePreference("IntakeUpDelay2", 0.5)));
    addParallel(new CascadeMove(hatchPos1));
  }
}
