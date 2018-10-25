package org.daredevils2512.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import org.daredevils2512.powerup.commands.*; //dad
import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.*;

/**
 *
 */
public class Elevator extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private static double pulseToFeet = 1 / 3944;
    
	private static WPI_TalonSRX frontElevator = new WPI_TalonSRX(5);
	private static WPI_TalonSRX rearElevator = new WPI_TalonSRX(8);
		
    private static DigitalInput elevatorLimitSwitch = new DigitalInput(4);
    
    public Elevator(){
        frontElevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		rearElevator.setInverted(true);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ElevatorManualRun());
    }
    
    public boolean getLimitSwitchValue() {
    	return elevatorLimitSwitch.get();
    }
    
    public void setSpeed(double speed) {
    	frontElevator.set(speed);
    	rearElevator.set(speed);
    }
    
    public double getLiftHeight() {
    	return frontElevator.getSelectedSensorVelocity(0) * pulseToFeet; //dividing it by that number to return in feet;
    }
    
    public void resetEncoder() {
    	frontElevator.setSelectedSensorPosition(0, 0, 0);
    }
    
    public boolean doubleInTolerance(double itemOne, double itemTwo, double tolerance) {
		double tol = tolerance / 2;
		return (itemOne - tol <= itemTwo && itemTwo <= itemOne + tol) &&
				(itemTwo - tol <= itemOne && itemOne <= itemTwo + tol);
	}
}

