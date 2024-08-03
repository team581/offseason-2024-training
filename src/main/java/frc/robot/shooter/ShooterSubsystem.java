package frc.robot.shooter;


import com.revrobotics.CANSparkMax;
import frc.robot.util.scheduling.LifecycleSubsystem;
import frc.robot.util.scheduling.SubsystemPriority;

public class ShooterSubsystem extends LifecycleSubsystem {
  private final CANSparkMax motor;
  private boolean intaking = false;
  private boolean shooting = false;
  public ShooterSubsystem(CANSparkMax motor) {
      super(SubsystemPriority.SHOOTER);

      this.motor = motor;
  }


  @Override
  public void robotPeriodic() {
    if(intaking == true) {
      motor.set(0.3);
  } else if(shooting == true) {
      motor.set(-0.5);
  } else {
      motor.set(0);
  }
}

  public void setIntakeMode(boolean in) {
    intaking = in;
  }
  public void setShootingMode(boolean shoot){
    shooting = shoot;
  }
}

