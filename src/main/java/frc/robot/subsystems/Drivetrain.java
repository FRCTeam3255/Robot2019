/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.GroupMotorControllers;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveArcade;
import frcteam3255.robotbase.SN_TalonSRX;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private SpeedControllerGroup leftTalons = null; 
  private SN_TalonSRX leftFrontTalon = null;
  private SN_TalonSRX leftMidTalon = null;
  private SN_TalonSRX leftBackTalon = null;

  private SpeedControllerGroup rightTalons = null;
  private SN_TalonSRX rightFrontTalon = null;
  private SN_TalonSRX rightMidTalon = null;
  private SN_TalonSRX rightBackTalon = null;

  private DifferentialDrive differentialDrive = null;

  public Drivetrain(){
    leftFrontTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_LEFT_FRONT_TALON);
    leftMidTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_LEFT_MID_TALON);
    leftBackTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_LEFT_BACK_TALON);
    leftTalons = new SpeedControllerGroup(leftFrontTalon, leftMidTalon, leftBackTalon);

    rightFrontTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_FRONT_TALON);
    rightMidTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_MID_TALON);
    rightBackTalon = new SN_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_BACK_TALON);
    rightTalons = new SpeedControllerGroup(rightFrontTalon, rightMidTalon, rightBackTalon);

    differentialDrive = new DifferentialDrive(leftTalons, rightTalons);
    differentialDrive.setSafetyEnabled(false);
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed){
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed, true);


  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveArcade());
  }
}
