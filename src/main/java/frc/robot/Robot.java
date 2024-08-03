// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Queuer.QueuerSubsystem;
import frc.robot.util.scheduling.LifecycleSubsystemManager;

public class Robot extends TimedRobot {
  public Robot() {
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

  private CANSparkMax motor = new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless);
  private XboxController driver = new XboxController(0);
  // TODO: Create an xbox controller for id 0

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    //  if the a button is pressed, do 80% voltage
    // otherwise, 0 voltage
    if (driver.getAButton() ) {
      motor.set(0.5);
    }
    else {
      //motor.set(0.0);
    }
    if (driver.getLeftTriggerAxis() > 0.5) {
      queuer.setIntakeMode(true);
    }

    if (driver.getRightTriggerAxis() > 0.5) {
      queuer.setShootingMode(true);
    }


  }

  private QueuerSubsystem queuer = new QueuerSubsystem(motor);


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
