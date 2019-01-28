/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frcteam3255.robotbase.SN_TalonSRX;

/**
 * Add your docs here.
 */
public class Cascade extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private SN_TalonSRX leftFrontTalon = null;
  private SN_TalonSRX leftBackTalon = null;
  private SN_TalonSRX rightFrontTalon = null;
  private SN_TalonSRX rightBackTalon = null;

  private Encoder liftEncoder = null;

  private DoubleSolenoid shiftSolenoid = null;
  private DoubleSolenoid climbSolenoid = null;
  private DoubleSolenoid lockSolenoid = null;

  private DigitalInput topSwitch = null;
  private DigitalInput bottomSwitch = null;

  public Cascade() {
    leftFrontTalon = new SN_TalonSRX(RobotMap.CASCADE_LEFT_FRONT_TALON);
    leftBackTalon = new SN_TalonSRX(RobotMap.CASCADE_LEFT_BACK_TALON);
    rightFrontTalon = new SN_TalonSRX(RobotMap.CASCADE_RIGHT_FRONT_TALON);
    rightBackTalon = new SN_TalonSRX(RobotMap.CASCADE_RIGHT_BACK_TALON);
    rightFrontTalon.setInverted(true);
    leftFrontTalon.setInverted(true);

    liftEncoder = new Encoder(RobotMap.CASCADE_LIFT_ENCODER_A, RobotMap.CASCADE_LIFT_ENCODER_B);

    // shiftSolenoid = new DoubleSolenoid(RobotMap.CASCADE_PCM,
    // RobotMap.CASCADE_SHIFT_SOLENOID_A,
    // RobotMap.CASCADE_SHIFT_SOLENOID_B);
    // climbSolenoid = new DoubleSolenoid(RobotMap.CASCADE_PCM,
    // RobotMap.CASCADE_CLIMB_SOLENOID_A,
    // RobotMap.CASCADE_CLIMB_SOLENOID_B);
    // lockSolenoid = new DoubleSolenoid(RobotMap.CASCADE_PCM,
    // RobotMap.CASCADE_LOCK_SOLENOID_A,
    // RobotMap.CASCADE_LOCK_SOLENOID_B);

    topSwitch = new DigitalInput(RobotMap.CASCADE_TOP_SWITCH);
    bottomSwitch = new DigitalInput(RobotMap.CASCADE_BOTTOM_SWITCH);
  }

  public boolean isTopSwitchClosed() {
    return topSwitch.get();
  }

  public boolean isBottomSwitchClosed() {
    return bottomSwitch.get();
  }

  public void deployClimb() {
    climbSolenoid.set(Value.kForward);
  }

  public void retractClimb() {
    climbSolenoid.set(Value.kReverse);
  }

  public void shiftCascade() {
    shiftSolenoid.set(Value.kForward);
  }

  public void shiftClimb() {
    shiftSolenoid.set(Value.kReverse);
  }

  public void lockCascade() {
    lockSolenoid.set(Value.kForward);
  }

  public void unlockCascade() {
    lockSolenoid.set(Value.kForward);
  }

  public double getLiftEncoderDistance() {
    return liftEncoder.get() / RobotPreferences.CASCADE_PULSES_PER_FOOT.getValue();
  }

  public double getLiftEncoderCount() {
    return liftEncoder.get();
  }

  public void resetLiftEncoder() {
    liftEncoder.reset();
  }

  public void setLiftSpeed(double speed) {
    speed = RobotPreferences.CASCADE_LIFT_SPEED.getValue();

    if (speed > 0) {
      if (isTopSwitchClosed()) {
        speed = 0.0;
      }
    } else if (speed < 0) {
      if (isBottomSwitchClosed()) {
        speed = 0.0;
      }
    }

    leftFrontTalon.set(speed);
    leftBackTalon.set(speed);
    rightFrontTalon.set(speed);
    rightBackTalon.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}