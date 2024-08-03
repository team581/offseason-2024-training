package frc.robot.shooter;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.util.scheduling.LifecycleSubsystem;
import frc.robot.util.scheduling.SubsystemPriority;

public class ShooterSubsystem extends LifecycleSubsystem{
    private final CANSparkMax motor;

    private boolean intaking = false;
    private boolean shooting = false;

    public ShooterSubsystem(CANSparkMax motor) {
        super(SubsystemPriority.SHOOTER);

        this.motor = motor;
    }

    @Override
    public void robotPeriodic() {
        if (intaking) {
          motor.set(0.3);
        } else if (shooting) {
          motor.set(-0.5);
        } else {
          motor.set(0);
        }
    }

  public void setIntakeMode(boolean on) {
    intaking = on;
  }

  public void setShootingMode(boolean on) {
    shooting = on;
  }

  public Command setIntakeCommand(boolean on) {
    return runOnce(
        () -> {
          setIntakeMode(on);
        });
  }

  public Command setShootingCommand(boolean on) {
    return runOnce(
        () -> {
            setShootingMode(on);
        });
  }
}


