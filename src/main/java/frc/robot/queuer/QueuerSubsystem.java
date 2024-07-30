package frc.robot.queuer;

import com.revrobotics.CANSparkMax;

import frc.robot.util.scheduling.LifecycleSubsystem;
import frc.robot.util.scheduling.SubsystemPriority;

public class QueuerSubsystem extends LifecycleSubsystem{
    private final CANSparkMax motor;

    private boolean intaking = false;
    private boolean shooting = false;

    // new QueuerSubsystem(motor)
    public QueuerSubsystem(CANSparkMax motor) {
        // lets LifecycleSubsystem know how to run file
        super(SubsystemPriority.QUEUER);

        // take the motor from constructor and store in field
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
        //if intaking set motor voltage to 0.3
        //if shooting set motor voltage to -0.5
        //if neither set voltage to 0
    }
public boolean setIntakeMode() {
   return intaking = true;
}
public boolean setShootingMode() {
    return shooting = true;
}
}