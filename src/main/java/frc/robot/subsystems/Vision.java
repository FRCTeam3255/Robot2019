/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsytem containing vision networktables and methoods
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static NetworkTable visionData = null;

  /**
   * Get vision data from limelight in networktables
   */
  public Vision() {
    visionData = NetworkTableInstance.getDefault().getTable("limelight");
  }

  /**
   * @return Check whether the limelight has found a target(1) or not(0)
   */
  public boolean targetFound() {
    return (visionData.getEntry("tv").getDouble(0.0) > 0.5);
  }

  /**
   * @return Horizontal offset from the target crosshair
   *         <ul>
   *         <li>ranges from -27 to 27 degrees</li>
   *         </ul>
   */
  public double getHorizontalOffset() {
    return -visionData.getEntry("tx").getDouble(-1000.0);
  }

  /**
   * @return Vertical offset from the target crosshair
   *         <ul>
   *         <li>ranges from -20.5 to 20.5 degrees</li>
   *         </ul>
   */
  public double getVerticalOffset() {
    return visionData.getEntry("ty").getDouble(-99.9);
  }

  /**
   * @return Horizontal sidelength of the rough bounding box
   *         <ul>
   *         <li>0 - 320 pixels</li>
   *         </ul>
   */
  public double getWidth() {
    return visionData.getEntry("thor").getDouble(-1.0);
  }

  /**
   * @return Target area
   *         <ul>
   *         <li>0% to 100% of image</li>
   *         </ul>
   */
  public double getTargetArea() {
    return visionData.getEntry("ta").getDouble(-99.9);
  }

  /**
   * @return Skew or rotation
   *         <ul>
   *         <li>-90 to 0 degrees</li>
   *         </ul>
   */
  public double getRotation() {
    return visionData.getEntry("ts").getDouble(-99.9);
  }

  /**
   * @return The distance from the camera to the target when we see the target
   */
  public double getDistance() {
    double w = getWidth();

    if ((w < 0) || (targetFound() == false)) {
      return -1.0;
    }

    return (8 * 265) / w;
  }

  /**
   * Toggle limelight LEDs
   */
  public void toggleLEDs() {
    if (visionData.getEntry("ledMode").getDouble(0) == 0) {
      visionData.getEntry("ledMode").setDouble(1);
    } else {
      visionData.getEntry("ledMode").setDouble(0);
    }
    try {
      Thread.sleep(250);
    } catch (InterruptedException e) {
    }
  }

  public void setDriverMode() {
    visionData.getEntry("camMode").setDouble(1);
  }

  public void setVisionMode() {
    visionData.getEntry("camMode").setDouble(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}