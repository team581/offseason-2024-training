// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import dev.doglog.DogLog;
import dev.doglog.DogLogOptions;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.generated.BuildConstants;
import frc.robot.queuer.QueuerSubsystem;
import frc.robot.shooter.ShooterSubsystem;
import frc.robot.util.scheduling.LifecycleSubsystemManager;

public class Robot extends TimedRobot {
  public Robot() {
    DogLog.setOptions(new DogLogOptions().withNtPublish(true));

    // Record metadata
    DogLog.log("Metadata/ProjectName", BuildConstants.MAVEN_NAME);
    DogLog.log("Metadata/BuildDate", BuildConstants.BUILD_DATE);
    DogLog.log("Metadata/GitSHA", BuildConstants.GIT_SHA);
    DogLog.log("Metadata/GitDate", BuildConstants.GIT_DATE);
    DogLog.log("Metadata/GitBranch", BuildConstants.GIT_BRANCH);

    switch (BuildConstants.DIRTY) {
      case 0:
        DogLog.log("Metadata/GitDirty", "All changes committed");
        break;
      case 1:
        DogLog.log("Metadata/GitDirty", "Uncomitted changes");
        break;
      default:
        DogLog.log("Metadata/GitDirty", "Unknown");
        break;
    }

    // This must be run before any commands are scheduled
    LifecycleSubsystemManager.getInstance().ready();

    SmartDashboard.putData(CommandScheduler.getInstance());

    configureBindings();
  }

  private void configureBindings() {
    // TODO: Add bindings
  }

  @Override
  public void robotInit() {}

  private final CANSparkMax queuerMotor =
      new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless);

  private final CommandXboxController controller = new CommandXboxController(0);

  // create QueuerSubsystem and provide motor from above
  private final QueuerSubsystem queuer = new QueuerSubsystem(queuerMotor);

  private final CANSparkMax shooterMotor = 
    new CANSparkMax(11, CANSparkLowLevel.MotorType.kBrushless);

  private final ShooterSubsystem shooter = new ShooterSubsystem(shooterMotor);

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // left trigger: intake
    // right trigger: shoot

    // queuer.setIntakeMode(controller.getLeftTriggerAxis() >= 0.5);
    // queuer.setShootingMode(controller.getRightTriggerAxis() >= 0.5);

    controller
        .leftTrigger()
        .onTrue(shooter.setIntakeCommand(true)
        .alongWith(queuer.setIntakeCommand(true)))
        .onFalse(shooter.setIntakeCommand(false)
        .alongWith(queuer.setIntakeCommand(false)));

    controller
        .a()
        .onTrue(shooter.setShootingCommand(true)
        .andThen(Commands.waitSeconds(1))
        .alongWith(queuer.setShootingCommand(true)))
        .onFalse(shooter.setShootingCommand(false)
        .alongWith(queuer.setShootingCommand(false)));

    // if (controller.getLeftTriggerAxis() >= 0.5) {
    //   queuer.setIntakeMode(true);
    // } else if (controller.getLeftTriggerAxis() < 0.5) {
    //   queuer.setIntakeMode(false);
    // }

    // if (controller.getRightTriggerAxis() >= 0.5) {
    //   queuer.setShootingMode(true);
    // } else if (controller.getRightTriggerAxis() < 0.5) {
    //   queuer.setShootingMode(false);
    // }

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
