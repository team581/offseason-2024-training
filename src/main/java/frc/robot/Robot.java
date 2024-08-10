// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import dev.doglog.DogLog;
import dev.doglog.DogLogOptions;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.generated.BuildConstants;
import frc.robot.queuer.QueuerSubsystem;
import frc.robot.shooter.ShooterSubsystem;
import frc.robot.util.scheduling.LifecycleSubsystemManager;


public class Robot extends TimedRobot {
  public Robot() {
    DogLog.setOptions(new DogLogOptions().withLogEntryQueueCapacity(1500).withNtPublish(true));

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
    controller.a().onTrue(Commands.runOnce(
      () -> shooter.setShootingMode(true)
    ));

    controller
      .rightTrigger(0.5)
      .onTrue(Commands.runOnce(
          () -> shooter.setShootingMode(true)
        )
        .andThen(Commands.waitSeconds(1.0))
        .andThen(Commands.runOnce(
          () -> queuer.setShootingMode(true)
        )
        .andThen(Commands.waitSeconds(0.5))
        .andThen(Commands.runOnce(
          () -> queuer.setShootingMode(true)
        ))
        .andThen(Commands.runOnce(
          () -> shooter.setShootingMode(true)
        ))
      ));
  }



  @Override
  public void robotInit() {}
// Create a motor here using a sparkMAX
  private CANSparkMax motor = new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless);
  private CANSparkMax motor2 = new CANSparkMax(11, CANSparkLowLevel.MotorType.kBrushless);
  private CommandXboxController controller = new CommandXboxController(0);
  private  QueuerSubsystem queuer = new QueuerSubsystem(motor);
  private ShooterSubsystem shooter = new ShooterSubsystem(motor2);

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();


    // if(controller.getAButton()) {
    //   motor.set(0.8);
    // } else {
    //  // motor.set(0.0);
    // }

    // if(controller.getLeftTriggerAxis() > 0.5) {
    //   queuer.setIntakeMode(true);
    //   shooter.setIntakeMode(true);
    // }else{
    //   queuer.setIntakeMode(false);
    //   shooter.setIntakeMode(false);
    // }

    // if(controller.getRightTriggerAxis() > 0.5) {
    //   shooter.setShootingMode(true);
    //   Commands.waitSeconds(1);
    //   queuer.setShootingMode(true);
    //   Commands.waitSeconds(0.5);
    //   //queuer.setShootingMode(false);
    //   //shooter.setShootingMode(false);
    // }
    // else{
    //   queuer.setShootingMode(false);
    //   shooter.setShootingMode(false);
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

    //No special commands here, on the subsystem
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
