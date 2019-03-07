/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeCargoCollectGroup extends CommandGroup {
  /**
   * <ul>
   * <li>Moves cascade to bottom</li>
   * <li>Deploys the intake</li>
   * <li>Retracts hatch hook and collects cargo</li>
   * <li>Half-second delay</li>
   * <li>Moves cascade to position 1</li>
   * </ul>
   */
  public IntakeCargoCollectGroup() {
    // addSequential(new CascadeBottomGroup());
    addSequential(new IntakeDeploy());
    addSequential(new IntakeCargoCollect());
    // addSequential(new DoDelay(new SN_DoublePreference("cargoCollectDelay",
    // 0.5)));
    // addSequential(new CascadePositionGroup(fieldHeights.LOW));
    // addSequential(new LightsAutoCommandFinish());
  }
}
