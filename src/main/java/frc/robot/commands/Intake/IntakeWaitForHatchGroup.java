/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.DoDelay;
import frc.robot.commands.Cascade.CascadePositionGroup;
import frc.robot.subsystems.Intake.fieldHeights;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class IntakeWaitForHatchGroup extends CommandGroup {
  /**
   * <ul>
   * <li>Waits until the hatch hook and intake is deployed</li>
   * <li>Retracts hatch hook</li>
   * <li>Half-second delay</li>
   * <li>Retracts intake</li>
   * <li>Moves cascade to position 1</li>
   * </ul>
   */
  public IntakeWaitForHatchGroup() {
    requires(Robot.m_cascade);
    requires(Robot.m_intake);

    addSequential(new IntakeWaitForHatch());
    addSequential(new IntakeHookRetract());
    addSequential(new DoDelay(new SN_DoublePreference("hatchPickUpDelay", 0.5)));
    addSequential(new IntakeRetract());
    addSequential(new CascadePositionGroup(fieldHeights.MED));
  }
}
