/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CascadeManualGroup extends CommandGroup {
	/**
	 * Unweights the cascade and locks when manual finishes
	 */
	public CascadeManualGroup() {
		addSequential(new CascadeShiftTo());
		addSequential(new CascadeUnweightGroup());
		addSequential(new CascadeManual());
		addSequential(new CascadeLockDogtooth());
	}
}
