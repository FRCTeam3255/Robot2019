package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

/**
 *
 */
public class DoDelay extends Command {

  private double expireTime;
  private SN_DoublePreference to;

  public DoDelay(SN_DoublePreference timeout) {
    to = timeout;
  }

  protected void startTimer() {
    expireTime = timeSinceInitialized() + this.to.getValue();
  }

  protected void initialize() {
    startTimer();
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return (timeSinceInitialized() >= expireTime);
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
