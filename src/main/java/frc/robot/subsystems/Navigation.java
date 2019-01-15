/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Navigation extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static AHRS ahrs = null;
  public static NetworkTable visionData = null;

  public Navigation() {
    visionData = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public boolean targetFound() {
    return visionData.getEntry("tv").getBoolean(false);
  }

  public double getVerticalOffset() {
    return visionData.getEntry("tx").getDouble(-99.9);
  }

  public double getHorizontalOffset() {
    return visionData.getEntry("ty").getDouble(-99.9);
  }

  public double getWidth() {
    return visionData.getEntry("thoriz").getDouble(-99.9);
  }

  public double getTargetArea() {
    return visionData.getEntry("ta").getDouble(-99.9);
  }

  public double getRotation() {
    return visionData.getEntry("ts").getDouble(-99.9);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
