/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbManual extends Command {

  double speed = 0.0;

  /**
   * Shifts to climber mode and manually moves climber with manipulator Y-axis
   */
  public ClimbManual() {
    requires(Robot.m_cascade);
    requires(Robot.m_intake);
  }

  @Override
  protected void initialize() {
    speed = 0.0;
    Robot.m_cascade.shiftClimb();
    Robot.m_intake.deployCargoIntake();
    Robot.m_intake.retractLinkage();
  }

  @Override
  protected void execute() {
    speed = Robot.m_oi.manipulatorStick.getYAxis();
    Robot.m_cascade.setLiftSpeed(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_cascade.setLiftSpeed(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}