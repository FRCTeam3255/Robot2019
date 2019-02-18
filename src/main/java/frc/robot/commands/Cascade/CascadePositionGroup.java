/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CascadePositionGroup extends CommandGroup {
	/**
	 * Unweights and locks the cascade after moving to a postion
	 * 
	 * @param position One of the 3 positions to place/retrieve a hatch/cargo
	 */
	public CascadePositionGroup(int position) {
		addSequential(new CascadeShiftTo());
		addSequential(new CascadeUnweight());
		addSequential(new CascadePosition(position));
		addSequential(new CascadeLockDogtooth());
	}
}
