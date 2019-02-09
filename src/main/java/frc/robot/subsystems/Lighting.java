/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.LightingFrequency;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

/**
 * Add your docs here.
 */
public class Lighting extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Spark lighting = null;

  public static final double RED = 0.60;
  public static final double BLUE = 0.87;
  public static final double YELLOW = 0.66;
  public static final double GREEN = 0.77;
  public static final double ORANGE = 0.63;
  public static final double OFF = 0.98;
  public static final double RED_LARSON = 0.0;

  public static SN_DoublePreference lightsHatch = new SN_DoublePreference("lightsHatch", BLUE); // solid blue
  public static SN_DoublePreference lightsOff = new SN_DoublePreference("lightsOff", RED_LARSON); // larson scanner red
  public static SN_DoublePreference lightsCargo = new SN_DoublePreference("lightsCargo", ORANGE); // solid orange

  public Lighting() {
    lighting = new Spark(RobotMap.LIGHTING_SPARK);
  }

  public void setLighting(double frequency) {
    lighting.set(frequency);
  }

  public void update() {
    if (Robot.m_intake.isHatchCollected()) {
      setLighting(lightsHatch.getValue());
    } else if (Robot.m_intake.isCargoCollected()) {
      setLighting(lightsCargo.getValue());
    } else {
      setLighting(lightsOff.getValue());
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new LightingFrequency());
  }
}
