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
 * Add your docs here.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static NetworkTable visionData = null;

  public Vision() {
    visionData = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public boolean targetFound() {
    return visionData.getEntry("tv").getBoolean(false);
  }

  public double getHorizontalOffset() {
    return -visionData.getEntry("tx").getDouble(-1000.0);
  }

  public double getVerticalOffset() {
    return visionData.getEntry("ty").getDouble(-99.9);
  }

  public double getWidth() {
    return visionData.getEntry("thor").getDouble(-1.0);
  }

  public double getTargetArea() {
    return visionData.getEntry("ta").getDouble(-99.9);
  }

  public double getRotation() {
    return visionData.getEntry("ts").getDouble(-99.9);
  }

  public double getDistance(){
    double w = getWidth();

    if(w < 0) {
      return -1.0;
    }

    return (8*265) / w;
  }
  public void toggleLEDs(){
    if(visionData.getEntry("ledMode").getDouble(0) == 0){
        visionData.getEntry("ledMode").setDouble(1);
    }
    else{
      visionData.getEntry("ledMode").setDouble(0);
    }
    try {
      Thread.sleep(250);
    }
    catch (InterruptedException e) {
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}