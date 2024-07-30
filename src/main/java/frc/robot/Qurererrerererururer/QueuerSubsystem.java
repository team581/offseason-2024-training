package frc.robot.Qurererrerererururer;

import com.revrobotics.CANSparkMax;

import frc.robot.util.scheduling.LifecycleSubsystem;
import frc.robot.until.scheduling.SubsystemPriority;

public class QueuerSubSystem {
  private final CANSparkMax motor;

  private boolean intaking = false;
  private boolean shooting = false;

  public QueuerSubSystem(CANSparkMax motor) {
    super(SubsystemPriority.QUEUER);
    this.motor = motor;
  }

 @Override
 public void robotPeriodic() {

   if (intaking) {
    motor.set(0.3);

} else if (shooting){ 
    motor.set(-0.5);
}
motor.set(0.0);
 }
}
