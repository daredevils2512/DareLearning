package org.daredevils2512.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.daredevils2512.powerup.Robot;
import org.daredevils2512.powerup.commands.Drive;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.*;



/**
 *
 */
public class Drivetrain extends Subsystem implements PIDOutput {
	PIDController controller;
    double pidOutput;
    
    private static WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(1);
	private static WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(3);
	private static WPI_TalonSRX rearLeftMotor = new WPI_TalonSRX(2);
    private static WPI_TalonSRX rearRightMotor = new WPI_TalonSRX(4);
    
    public static SpeedControllerGroup leftSide = new SpeedControllerGroup(frontLeftMotor, rearLeftMotor);
	public static SpeedControllerGroup rightSide = new SpeedControllerGroup(frontRightMotor, rearRightMotor);
    public static DifferentialDrive chassis = new DifferentialDrive(leftSide, rightSide);

    public static Encoder leftEncoder = new Encoder(0, 1, false, CounterBase.EncodingType.k4X);
	public static Encoder rightEncoder = new Encoder(2, 3, true, CounterBase.EncodingType.k4X);
    public static double encoderDistancePerPulse = 0.0236065636;
    
	public static DoubleSolenoid shifter = new DoubleSolenoid(4, 5);
	
    
    public Drivetrain() {
        controller = new PIDController(0, 0, 0, Robot.m_navX, this);
        
		leftEncoder.setDistancePerPulse(encoderDistancePerPulse);
		rightEncoder.setDistancePerPulse(encoderDistancePerPulse);
		
	}

	// Put methods for controlling this subsystem


	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    
    public void driveRobotTank(double leftSpeed, double rightSpeed) {
    	chassis.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void driveRobotPID(double speed) {
    	chassis.tankDrive(speed + (pidOutput / 4d), speed + (pidOutput / 4d));
    }
    
    public void turnRobotPID(double speed) {
    	chassis.tankDrive(pidOutput * speed, pidOutput * speed);
    }
    
    public void driveRobotArcade(double move, double turn) {
    	chassis.arcadeDrive(move, turn);
    }
    
    public int getLeftEncoderValue() {
    	return leftEncoder.get();
    }
    
    public int getRightEncoderValue() {
    	return rightEncoder.get();
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public DoubleSolenoid.Value getShifterPos() {
    	return shifter.get();
    }
    
    public void shift(DoubleSolenoid.Value shiftPos) {
    	shifter.set(shiftPos);
    }

	@Override
	public void pidWrite(double output) {
		pidOutput = output;
	}
    
}

