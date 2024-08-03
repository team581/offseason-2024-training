package frc.robot.Queuer;

import com.revrobotics.CANSparkMax;

import frc.robot.util.scheduling.LifecycleSubsystem;
import frc.robot.util.scheduling.SubsystemPriority;

public class QueuerSubsystem extends LifecycleSubsystem{
  private final CANSparkMax motor;

  private boolean intaking = false;
  private boolean shooting = false;

  public QueuerSubsystem(CANSparkMax motor) {
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
    else {
      motor.set(0.0);
    }
  }

  public void setIntakeMode (boolean intake) {
    this.intaking = intake;
  }

  public void setShootingMode (boolean shoot) {
    this.shooting = shoot;
  }


}
