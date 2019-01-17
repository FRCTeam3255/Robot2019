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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
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
    // NavX
      try{
          ahrs = new AHRS(SPI.Port.kMXP);
      } catch (RuntimeException ex) {
          DriverStation.reportError("Error installing navX MXP: " + ex.getMessage(), true);
      }

    //Vision
    visionData = NetworkTableInstance.getDefault().getTable("limelight");
  }

  //NavX
  public double getYaw(){
    return ahrs.getYaw();
  }

  public double getPitch(){
    return ahrs.getPitch();
  }

  public double getRoll(){
    return ahrs.getRoll();
  }

  public void resetYaw(){
    ahrs.reset();

    try {
      Thread.sleep(250);
    }
    catch (InterruptedException e) {
    }

    ahrs.zeroYaw();
  }

  public void resetPitch(){
    ahrs.reset();
  }

  public boolean isCalibrating(){
    return ahrs.isCalibrating();
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
    return visionData.getEntry("thor").getDouble(-99.9);
  }

  public double getTargetArea() {
    return visionData.getEntry("ta").getDouble(-99.9);
  }

  public double getRotation() {
    return visionData.getEntry("ts").getDouble(-99.9);
  }
  public double getDistance(){
    return (8*265)/getWidth();
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
