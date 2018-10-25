package org.daredevils2512.powerup.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.*;

import com.ctre.phoenix.motorcontrol.can.*;

/**
 *
 */
public class Intake extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private static WPI_TalonSRX intakeLeftMotor = new WPI_TalonSRX(6);
	private static WPI_TalonSRX intakeRightMotor = new WPI_TalonSRX(7);
	private static DoubleSolenoid intakeArmsSolenoid = new DoubleSolenoid(6, 7);
	private static DoubleSolenoid intakeDeploySolenoid = new DoubleSolenoid(0, 1);
	private static DigitalInput intakeLimitSwitch = new DigitalInput(5);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean getLimitSwitchValue() {
    	return intakeLimitSwitch.get();
    }
    
    public void actuateArms(DoubleSolenoid.Value actuateDir) {
    	intakeArmsSolenoid.set(actuateDir);
    }
    
    public void setIntakeSpeed(double speed) {
    	intakeLeftMotor.set(speed);
    	intakeRightMotor.set(-speed);
    }
    
    public void actuateDeploy(DoubleSolenoid.Value actuateDir) {
    	intakeDeploySolenoid.set(actuateDir);
    }
}

