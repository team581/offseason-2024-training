// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.proto.Controller;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Queuer.QueuerSubsystem;
import frc.robot.shooter.ShooterSubSystem;
import frc.robot.util.scheduling.LifecycleSubsystemManager;

public class Robot extends TimedRobot {
  public Robot() {
    // This must be run before any commands are scheduled
    LifecycleSubsystemManager.getInstance().ready();

    SmartDashboard.putData(CommandScheduler.getInstance());

    configureBindings();
  }

  private void configureBindings() {
    driver.a().onTrue(Commands.runOnce(() -> shooter.setShootingCommand(true)));
   }

  @Override
  public void robotInit() {}

  private CANSparkMax motor = new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless);
  private CommandXboxController driver = new CommandXboxController(0);
  private QueuerSubsystem queuer = new QueuerSubsystem(motor);
  private ShooterSubSystem shooter = new ShooterSubSystem(motor);

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // if (driver.getAButton() ) {
    //   motor.set(0.5);
    // }
    // else {
    //   motor.set(0.0);
    // }
    // if (driver.getLeftTriggerAxis() > 0.5) {
    //   queuer.setIntakeMode(true);
    // }
    // else if (driver.getLeftTriggerAxis() < 0.5) {
    //   queuer.setIntakeMode(false);
    // }
    // if (driver.getRightTriggerAxis() > 0.5) {
    //   shooter.setShootingMode(true);
    //   Commands.waitSeconds(1);
    //   queuer.setShootingMode(true);
    //   Commands.waitSeconds(0.5);
    //   shooter.setShootingMode(false);
    //   queuer.setIntakeMode(false);
    // }
    // else if (driver.getRightTriggerAxis() < 0.5) {
    //   shooter.setShootingMode(false);
    // }



  // driver
  //     .leftTrigger()
  //     .onTrue(shooter.setIntakeCommand(true))
  //     .with(queuer.setIntakeCommand(true))
  //     .onFalse(shooter.setIntakeCommand(false))
  //     .with(queuer.setIntakeCommand(false));

  // driver
  //     .getRightTriggerAxis()
  //     .onTrue(shooter.setShootingCommand(true))
  //     .then(Commands.waitSeconds(1))
  //     .with(queuer.setShootingCommand(true))
  //     .then(Commands.waitSeconds(3))
  //     .with(shooter.setShootingCommand(false))
  //     .with(queuer.setShootingCommand(false));

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
