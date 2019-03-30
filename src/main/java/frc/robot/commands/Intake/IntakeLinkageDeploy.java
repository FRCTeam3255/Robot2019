/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

/**
 * Add your docs here.
 */
public class IntakeLinkageDeploy extends Command {
  /**
   * Add your docs here.
   */
  private double expireTime;
  private double to;

  public IntakeLinkageDeploy() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_intake);
    to = 0.5;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    expireTime = timeSinceInitialized() + to;
    Robot.m_intake.deployLinkage();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_intake.shootCargo(-0.5);
  }

  @Override
  protected boolean isFinished() {
    return (timeSinceInitialized() >= expireTime);
  }

  protected void end() {
    Robot.m_intake.holdCargo();
  }
}
