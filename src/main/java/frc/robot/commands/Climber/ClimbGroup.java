/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Drive.DriveToWall;
import frcteam3255.robotbase.Preferences.SN_DoublePreference;

public class ClimbGroup extends CommandGroup {
  private SN_DoublePreference speed1 = new SN_DoublePreference("speed1", -1.0);
  private SN_DoublePreference speed2 = new SN_DoublePreference("speed2", -1.0);
  private SN_DoublePreference speed3 = new SN_DoublePreference("speed3", -1.0);

  private SN_DoublePreference decel1 = new SN_DoublePreference("decel1", 0.2);
  private SN_DoublePreference decel2 = new SN_DoublePreference("decel2", 0.2);
  private SN_DoublePreference decel3 = new SN_DoublePreference("decel3", 0.2);

  private SN_DoublePreference highPosition = new SN_DoublePreference("highPosition", -10.0);
  private SN_DoublePreference lowPosition = new SN_DoublePreference("lowPosition", 0.0);

  /**
   * Add your docs here.
   */
  public ClimbGroup() {
    addSequential(new ClimbDeploy());
    addSequential(new DriveToWall(speed1, decel1));
    addSequential(new ClimbPosition(highPosition));
    addSequential(new DriveToWall(speed2, decel2));
    addSequential(new ClimbPosition(lowPosition));
    addSequential(new DriveToWall(speed3, decel3));
  }
}
