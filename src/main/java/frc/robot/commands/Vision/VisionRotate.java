/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Lighting;
import frc.robot.subsystems.VisionRotatePID;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class VisionRotate extends Command {

	private VisionRotatePID rotatePID;
	private SN_DoublePreference pref_timeout = new SN_DoublePreference("VisionRotateDistance_timeout", 100.0);
	private String name;

	private double expireTime = 0.0;
	private double rotateSpeed = 0.0;

	/**
	 * <li>Assists the driver with vision. If output is invalid lets driver
	 * control.</li>
	 * <li>Driver RBump uses slow speed.</li>
	 * <li>Sets lighting for targeting:</li>
	 * <ul>
	 * <li>Green: Maintained distance/offset setpoints.</li>
	 * <li>Yellow: Sees target.</li>
	 * <li>Red: No target.</li>
	 * </ul>
	 * 
	 * @param inches
	 * @param offset
	 * @param commandName
	 */
	public VisionRotate(SN_DoublePreference offset, String commandName) {
		requires(Robot.m_drivetrain);
		requires(Robot.m_lighting);

		rotatePID = new VisionRotatePID();

		rotatePID.setSetpoint(offset);
		name = commandName;
	}

	public void setTimeout(SN_DoublePreference timeout) {
		pref_timeout = timeout;
	}

	public VisionRotatePID getRotatePID() {
		return rotatePID;
	}

	@Override
	protected void initialize() {
		Robot.m_vision.setVisionMode();
		Robot.m_telemetry
				.setCommandStatus("Starting DriveDistanceRotateVision " + name + ": " + rotatePID.getSetpoint());
		expireTime = timeSinceInitialized() + pref_timeout.getValue();

		rotatePID.enable();
	}

	@Override
	protected void execute() {
		Robot.m_telemetry
				.setCommandStatus("Executing DriveDistanceRotateVision " + name + ": " + rotatePID.getSetpoint());

		if (rotatePID.isOutputValid() == false) {
			rotateSpeed = Robot.m_oi.driverStick.getArcadeRotate();
		} else {
			rotateSpeed = rotatePID.getOutput();
		}

		Robot.m_drivetrain.arcadeDrive(0.0, rotateSpeed, false);

		if (rotatePID.onRawTarget()) {
			Robot.m_lighting.setLighting(Lighting.GREEN);
		} else if (Robot.m_vision.targetFound()) {
			Robot.m_lighting.setLighting(Lighting.YELLOW);
		} else {
			Robot.m_lighting.setLighting(Lighting.RED);
		}
	}

	@Override
	protected boolean isFinished() {
		boolean rotateTarget = rotatePID.onRawTarget();
		double timeNow = timeSinceInitialized();

		boolean finished = ((rotateTarget) || (timeNow >= expireTime));

		return finished;
	}

	@Override
	protected void end() {
		Robot.m_telemetry
				.setCommandStatus("Finishing DriveDistanceRotateVision " + name + ": " + rotatePID.getSetpoint());
		rotatePID.disable();
		Robot.m_drivetrain.arcadeDrive(0.0, 0.0, false);
		Robot.m_vision.setDriverMode();
	}

	@Override
	protected void interrupted() {
		end();
	}
}