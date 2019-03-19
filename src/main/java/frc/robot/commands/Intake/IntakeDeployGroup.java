/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeDeployGroup extends CommandGroup {
  /**
   * <ul>
   * <li>Retracts hatch ejector pistons</li>
   * <li>Unweights and locks cascade at bottom</li>
   * <li>Deploys the intake and hatch finger</li>
   * </ul>
   */
  public IntakeDeployGroup() {
    // addSequential(new CascadeUnweightGroup());
    // addSequential(new CascadeBottom());
    // addSequential(new IntakeCargoDeploy());
    // addSequential(new IntakeFingerDeploy());
    // addSequential(new IntakeWaitForHatchGroup());
    // addSequential(new CascadePositionGroup(fieldHeights.LOW));
    // addSequential(new LightsAutoCommandFinish());
  }
}
