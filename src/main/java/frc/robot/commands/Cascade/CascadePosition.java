/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Cascade;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CascadePID;
import frc.robot.subsystems.Intake.fieldHeights;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class CascadePosition extends Command {

	private CascadePID pid;
	private SN_DoublePreference pref_timeout = new SN_DoublePreference("Lift Timeout", 100.0);
	private double expireTime = 0.0;
	private fieldHeights setpoint;
	private double speed = 0;

	public CascadePosition(fieldHeights position) {
		requires(Robot.m_cascade);
		setpoint = position;
		pid = new CascadePID();
	}

	public void setTimeout(SN_DoublePreference timeout) {
		pref_timeout = timeout;
	}

	public CascadePID getPID() {
		return pid;
	}

	@Override
	protected void initialize() {
		expireTime = timeSinceInitialized() + pref_timeout.getValue();
		pid.setSetpoint(Robot.m_intake.getSetpoint(setpoint));
		pid.enable();

		Robot.m_telemetry.setCommandStatus("Starting CascadeLift" + ": " + pid.getSetpoint());
	}

	@Override
	protected void execute() {
		Robot.m_telemetry.setCommandStatus("Executing CascadeLift");
		speed = pid.getOutput();
		Robot.m_cascade.setLiftSpeed(speed);
	}

	@Override
	protected boolean isFinished() {
		boolean distanceTarget = pid.onRawTarget();
		double timeNow = timeSinceInitialized();
		boolean finished = (distanceTarget || (timeNow >= expireTime)
				|| ((speed > 0) && Robot.m_cascade.isTopSwitchClosed())
				|| ((speed < 0) && Robot.m_cascade.isBottomSwitchClosed()));

		return finished;
	}

	@Override
	protected void end() {
		Robot.m_telemetry.setCommandStatus("Finishing CascadeLift" + ": " + pid.getSetpoint());
		pid.disable();
		Robot.m_cascade.setLiftSpeed(0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}