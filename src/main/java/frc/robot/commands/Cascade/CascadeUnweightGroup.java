/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotPreferences;
import frc.robot.commands.DoDelay;

public class CascadeUnweightGroup extends CommandGroup {

  public CascadeUnweightGroup() {
    addSequential(new CascadeUnlockDogtooth());
    addSequential(new DoDelay(RobotPreferences.CLIMB_LIFT_DELAY));
    addSequential(new CascadeUnweight());
  }
}
