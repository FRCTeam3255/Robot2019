/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.UpdateLighting;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

/**
 * Subystem conrolling the REV BLinkin module
 */
public class Lighting extends Subsystem {
  private Spark lighting = null;

  // Color frequencies
  public static final double RED = 0.60;
  public static final double BLUE = 0.87;
  public static final double YELLOW = 0.66;
  public static final double GREEN = 0.77;
  public static final double ORANGE = 0.63;
  public static final double OFF = 0.98;
  public static final double RED_LARSON = 0.0;
  public static final double VIOLET = 0.91;
  public static final double HOT_PINK = 0.57;

  public static SN_DoublePreference LIGHTS_HATCH = new SN_DoublePreference("lightsHatch", BLUE); // solid blue
  public static SN_DoublePreference LIGHTS_OFF = new SN_DoublePreference("lightsOff", RED_LARSON); // larson scanner red
  public static SN_DoublePreference LIGHTS_CARGO = new SN_DoublePreference("lightsCargo", ORANGE); // solid orange
  public static SN_DoublePreference LIGHTS_TARGET_FOUND = new SN_DoublePreference("lightsTargetFound", YELLOW);
  public static SN_DoublePreference LIGHTS_TARGET_NOT_FOUND = new SN_DoublePreference("lightsTargetNotFound", RED);
  public static SN_DoublePreference LIGHTS_WAIT_FOR_HATCH = new SN_DoublePreference("WaitForHatch", VIOLET);
  public static SN_DoublePreference LIGHTS_AUTO_COMMAND_FINISH = new SN_DoublePreference("autoCommandFinish", HOT_PINK);

  /**
   * Create the spark light controller
   */
  public Lighting() {
    lighting = new Spark(RobotMap.LIGHTING_SPARK);
  }

  public void setLighting(double frequency) {
    lighting.set(frequency);
  }

  /**
   * Update the lighting for various scenarios
   */
  public void update() {
    if (RobotController.isBrownedOut() || !(DriverStation.getInstance().isDSAttached())) {
      setLighting(OFF);
    } else if (Robot.m_oi.driverStick.btn_LTrig.get()) {
      if (Robot.m_vision.targetFound()) {
        setLighting(LIGHTS_TARGET_FOUND.getValue());
      } else {
        setLighting(LIGHTS_TARGET_NOT_FOUND.getValue());
      }
    } else if (Robot.m_intake.isHatchCollected()) {
      setLighting(LIGHTS_HATCH.getValue());
    } else if (Robot.m_intake.isCargoCollected()) {
      setLighting(LIGHTS_CARGO.getValue());
    } else {
      setLighting(LIGHTS_OFF.getValue());
    }

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new UpdateLighting());
  }
}
