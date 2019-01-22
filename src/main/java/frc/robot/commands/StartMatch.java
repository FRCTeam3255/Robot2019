/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class StartMatch extends CommandGroup {
  // d1 preferences are used for backing off the ramp
  private static SN_DoublePreference d1SetPoint = new SN_DoublePreference("D1SetPoint", -60.0);
  private static SN_DoublePreference d1Tolerance = new SN_DoublePreference("D1Tolerance", 5.0);
  private static SN_DoublePreference d1DefaultSpeed = new SN_DoublePreference("D1DefaultSpeed", 0.0);

  // r2 preferences are used for rotating to the right until we see the vision target at the feeder station
  private static SN_DoublePreference r2SetPoint = new SN_DoublePreference("R2SetPoint", 0.0);
  private static SN_DoublePreference r2Tolerance = new SN_DoublePreference("R2Tolerance", 5.0);
  private static SN_DoublePreference r2DefaultSpeed = new SN_DoublePreference("R2DefaultSpeed", 0.2);

  // d3 and r3 preferences are used for driving to the feeder station with vision
  private static SN_DoublePreference d3SetPoint = new SN_DoublePreference("D3SetPoint", 12.0);
  private static SN_DoublePreference d3Tolerance = new SN_DoublePreference("D3Tolerance", 2.0);
  // private static SN_DoublePreference d3DefaultSpeed = new SN_DoublePreference("D3DefaultSpeed", 0.0);

  private static SN_DoublePreference r3SetPoint = new SN_DoublePreference("R3SetPoint", 0.0);
  private static SN_DoublePreference r3Tolerance = new SN_DoublePreference("R3Tolerance", 2.5);
  // private static SN_DoublePreference r3DefaultSpeed = new SN_DoublePreference("R3DefaultSpeed", 0.0);

  // d4 and r4 preferences are for backing away from the feeder station rotating to the right as we back up
  private static SN_DoublePreference d4SetPoint = new SN_DoublePreference("D4SetPoint", 24.0);
  private static SN_DoublePreference d4Tolerance = new SN_DoublePreference("D4Tolerance", 4.0);
  // private static SN_DoublePreference d4DefaultSpeed = new SN_DoublePreference("D3DefaultSpeed", 0.0);

  private static SN_DoublePreference r4SetPoint = new SN_DoublePreference("R4SetPoint", -20.0);
  private static SN_DoublePreference r4Tolerance = new SN_DoublePreference("R4Tolerance", 5.0);
  // private static SN_DoublePreference r4DefaultSpeed = new SN_DoublePreference("R4DefaultSpeed", 0.0);

  // r5 preferences are for rotating to the right until we see the vision target at the rocket
  private static SN_DoublePreference r5SetPoint = new SN_DoublePreference("R5SetPoint", 0.0);
  private static SN_DoublePreference r5Tolerance = new SN_DoublePreference("R5Tolerance", 5.0);
  private static SN_DoublePreference r5DefaultSpeed = new SN_DoublePreference("R5DefaultSpeed", 0.2);

  // d6 and r6 drive to the rocket with vision
  private static SN_DoublePreference d6SetPoint = new SN_DoublePreference("D6SetPoint", 12.0);
  private static SN_DoublePreference d6Tolerance = new SN_DoublePreference("D6Tolerance", 2.0);
  // private static SN_DoublePreference d6DefaultSpeed = new SN_DoublePreference("D6DefaultSpeed", 0.0);

  private static SN_DoublePreference r6SetPoint = new SN_DoublePreference("R6SetPoint", 0.0);
  private static SN_DoublePreference r6Tolerance = new SN_DoublePreference("R6Tolerance", 2.5);
  // private static SN_DoublePreference r6DefaultSpeed = new SN_DoublePreference("R6DefaultSpeed", 0.0);
  
  public StartMatch() {
    // drive in reverse off the platform
    DriveDistance d1 = new DriveDistance(d1SetPoint);
    d1.getPID().setTolerance(d1Tolerance);
    d1.getPID().setDefaultOutput(d1DefaultSpeed);

    DriveRotateVision r2 = new DriveRotateVision(r2SetPoint);
    r2.getPID().setTolerance(r2Tolerance);
    r2.getPID().setDefaultOutput(r2DefaultSpeed);

    DriveDistanceRotateVision d3r3 = new DriveDistanceRotateVision(d3SetPoint, r3SetPoint);
    d3r3.getDistancePID().setTolerance(d3Tolerance);
    d3r3.getRotatePID().setTolerance(r3Tolerance);

    DriveDistanceRotateVision d4r4 = new DriveDistanceRotateVision(d4SetPoint, r4SetPoint);
    d4r4.getDistancePID().setTolerance(d4Tolerance);
    d4r4.getRotatePID().setTolerance(r4Tolerance);

    DriveRotateVision r5 = new DriveRotateVision(r5SetPoint);
    r5.getPID().setTolerance(r5Tolerance);
    r5.getPID().setDefaultOutput(r5DefaultSpeed);

    DriveDistanceRotateVision d6r6 = new DriveDistanceRotateVision(d6SetPoint, r6SetPoint);
    d6r6.getDistancePID().setTolerance(d6Tolerance);
    d6r6.getRotatePID().setTolerance(r6Tolerance);

    addSequential(d1);
    addSequential(r2);
    addSequential(d3r3);
    addSequential(d4r4);
    addSequential(r5);
    addSequential(d6r6);

    // rotate to the right until we see the vision target at the feeder station
    // addSequential(new VisionRotate(r2SetPoint, r2Tolerance, r2DefaultSpeed));

    // drive to the feeder station with vision
    // addSequential(new VisionRotateDistance(r3SetPoint, r3Tolerance, r3DefaultSpeed, d3SetPoint, d3Tolerance, d3DefaultSpeed));

    // get the hatch

    // back away from the feeder station rotating to the right as we back up
    // addSequential(new VisionRotateDistance(r4SetPoint, r4Tolerance, r4DefaultSpeed, d4SetPoint, d4Tolerance, d4DefaultSpeed));
    
    // rotate to the right until we see the vision target at the rocket
    // addSequential(new VisionRotate(r5SetPoint, r5Tolerance, r5DefaultSpeed));

    // drive to the rocket with vision
    // addSequential(new VisionRotateDistance(r6SetPoint, r6Tolerance, r6DefaultSpeed, d6SetPoint, d6Tolerance, d6DefaultSpeed));
  }
}
