/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoDelay;
import frc.robot.commands.LightsAutoCommandFinish;
import frc.robot.commands.Cascade.CascadeBottom;
import frc.robot.commands.Cascade.CascadeBottomGroup;
import frc.robot.commands.Cascade.CascadePositionGroup;
import frc.robot.subsystems.Intake.fieldHeights;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class IntakeCargoCollectGroup extends CommandGroup {
  /**
   * <ul>
   * <li>Moves cascade to bottom</li>
   * <li>Deploys the intake</li>
   * <li>Retracts hatch finger and collects cargo</li>
   * <li>Half-second delay</li>
   * <li>Moves cascade to position 1</li>
   * </ul>
   */
  public IntakeCargoCollectGroup() {
    addSequential(new CascadeBottomGroup());
    addSequential(new IntakeLinkageDeploy());
    addSequential(new IntakeFingerRetract());
    addSequential(new IntakeCargoDeploy());
    addSequential(new IntakeCargoCollect());
    addSequential(new DoDelay(new SN_DoublePreference("cargoCollectDelay", 0.5)));
    addSequential(new CascadePositionGroup(fieldHeights.LOW));
    addSequential(new LightsAutoCommandFinish());
  }
}
