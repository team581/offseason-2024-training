package frc.robot.queuer;

import com.revrobotics.CANSparkMax;
import frc.robot.util.scheduling.LifecycleSubsystem;
import frc.robot.util.scheduling.SubsystemPriority;

public class QueuerSubsystem extends LifecycleSubsystem {
  private final CANSparkMax motor;
  private boolean intaking = false;
  private boolean shooting = false;
  public QueuerSubsystem(CANSparkMax motor) {
      super(SubsystemPriority.QUEUER);

      this.motor = motor;
  }


  @Override
  public void robotPeriodic() {
      //If no intaking. set motor voltage to 0.3
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






