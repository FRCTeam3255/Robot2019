/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotPreferences;
import frc.robot.commands.Cascade.CascadeDeployClimb;
import frc.robot.commands.Cascade.CascadeMove;
import frc.robot.commands.Cascade.CascadeResetEncoder;
import frc.robot.commands.Drive.DriveDistance;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class Climb extends CommandGroup {
	private static final SN_DoublePreference climbLiftD1 = new SN_DoublePreference("climbLiftD1", -25.0);
	private static final SN_DoublePreference climbDriveD1 = new SN_DoublePreference("climbDriveD1", -20.0);
	private static final SN_DoublePreference climbLiftD2 = new SN_DoublePreference("climbLiftD2", 0.0);
	private static final SN_DoublePreference climbDriveD2 = new SN_DoublePreference("climbDriveD2", -20.0);

	/**
	 * Add your docs here.
	 */
	public Climb() {
		addSequential(new CascadeMove(0));
		addSequential(new CascadeResetEncoder());
		addSequential(new ClimbShiftTo());
		addSequential(new CascadeDeployClimb());
		addSequential(new ClimberLift(climbLiftD1));
		addSequential(new DriveDistance(climbDriveD1, "Climb Drive #1"));
		addSequential(new ClimberLift(climbLiftD2));
		addSequential(new DriveDistance(climbDriveD2, "Climb Drive #2"));
	}
}
