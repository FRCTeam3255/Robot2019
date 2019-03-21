/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DrivetrainDistancePID;
import frc.robot.subsystems.NavXRotatePID;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class DriveRotateDistance extends Command {
  private DrivetrainDistancePID distancePID;
  private NavXRotatePID rotatePID;
  private SN_DoublePreference pref_timeout = new SN_DoublePreference("DriveDistance_timeout", 10.0);

  private double expireTime = 0.0;
  private String name;

  public DriveRotateDistance(SN_DoublePreference inches, SN_DoublePreference degrees, String commandName) {
    requires(Robot.m_drivetrain);

    distancePID = new DrivetrainDistancePID();
    rotatePID = new NavXRotatePID();

    distancePID.setSetpoint(inches);
    rotatePID.setSetpoint(degrees);
    name = commandName;
  }

  public void setTimeout(SN_DoublePreference timeout) {
    pref_timeout = timeout;
  }

  public DrivetrainDistancePID getPID() {
    return distancePID;
  }

  public NavXRotatePID getRotatePID() {
    return rotatePID;
  }

  @Override
  protected void initialize() {
    Robot.m_telemetry.setCommandStatus("Starting " + name + ": " + distancePID.getSetpoint() + " ");
    expireTime = timeSinceInitialized() + pref_timeout.getValue();

    Robot.m_drivetrain.resetEncoderCount();
    Robot.m_navigation.resetYaw();

    distancePID.enable();
    rotatePID.enable();
  }

  @Override
  protected void execute() {
    Robot.m_telemetry
        .setCommandStatus("Executing " + name + ": D:" + distancePID.getSetpoint() + " + R:" + rotatePID.getSetpoint());
    double moveSpeed = distancePID.getOutput();
    double rotateSpeed = rotatePID.getOutput();

    Robot.m_drivetrain.arcadeDrive(moveSpeed, rotateSpeed, false);
  }

  @Override
  protected boolean isFinished() {
    boolean distanceTarget = distancePID.onRawTarget();
    boolean rotateTarget = rotatePID.onRawTarget();
    double timeNow = timeSinceInitialized();

    boolean finished = (distanceTarget && rotateTarget || (timeNow >= expireTime));

    return finished;
  }

  @Override
  protected void end() {
    Robot.m_telemetry.setCommandStatus("Finishing " + name + ": " + distancePID.getSetpoint() + "");
    distancePID.disable();
    rotatePID.disable();
    Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
