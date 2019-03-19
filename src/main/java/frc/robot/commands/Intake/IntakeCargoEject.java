/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeCargoEject extends Command {

  double speed = 0.0;

  public IntakeCargoEject() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_intake.deployLinkage();
    Robot.m_intake.retractFinger();
    Robot.m_intake.deployCargoIntake();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    this.speed = Robot.m_oi.manipulatorStick.getDialAxis();

    if (speed < 0.25) {
      speed = 0.25;
    }
    Robot.m_intake.shootCargo(-speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_intake.holdCargo();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
