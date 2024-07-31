// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.queuer;

import com.revrobotics.CANSparkMax;
import frc.robot.util.scheduling.LifecycleSubsystem;
import frc.robot.util.scheduling.SubsystemPriority;

public class QueuerSubsystem extends LifecycleSubsystem {
  private final CANSparkMax motor;

  // These booleans represent the state of the subsystem (what it's doing)
  private boolean intaking = false;
  private boolean shooting = false;

  // new QueuerSubsystem(motor)
  public QueuerSubsystem(CANSparkMax motor) {
    // Add this line to let LifecycleSubsystem know how to run this file
    super(SubsystemPriority.QUEUER);

    // Take the motor from the constructor, and store it in a field
    this.motor = motor;
  }

  @Override
  public void robotPeriodic() {
    // If we are intaking, set motor voltage to 0.3
    // If we are shooting, set motor voltage to -0.5
    // If we are doing neither, set the motor voltage to 0

    // fill this in on your own !
    motor.set(0.123); // set motor to a percentage
  }

  // Once you're done adding in the code to process those states, let's add a way to update the
  // states.

  // Please add in a method for setting intaking to be true/false (ex. setIntakeMode())
  // Then add in a method that does the same but for shooting mode
}
