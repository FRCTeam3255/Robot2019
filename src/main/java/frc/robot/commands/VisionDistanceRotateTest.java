/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Drive.DriveDistanceRotateVision;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class VisionDistanceRotateTest extends CommandGroup {
	/**
	 * Add your docs here.
	 */
	public VisionDistanceRotateTest() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.

		DriveDistanceRotateVision driveVisionTest = new DriveDistanceRotateVision(new SN_DoublePreference("VD", 30.0),
				new SN_DoublePreference("VR", 0.0), "driveVisionTest");
		driveVisionTest.getDistancePID().setTolerance(new SN_DoublePreference("testDTolerance", 0.0));
		driveVisionTest.getRotatePID().setTolerance(new SN_DoublePreference("testRTolerance", 0.0));

		addSequential(driveVisionTest);
	}
}
