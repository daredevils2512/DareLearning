package org.daredevils2512.powerup.commands;

import org.daredevils2512.powerup.Robot;

import edu.wpi.first.wpilibj.command.Command;

import org.daredevils2512.powerup.OI;

/**
 *
 */
public class Drive extends Command {
    public Drive() {
        requires(Robot.m_drivetrain);
    }

    protected void execute() {
        double move = OI.driver.getX();
        double turn = OI.driver.getY();

        Robot.m_drivetrain.driveRobotArcade(move, turn);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.m_drivetrain.driveRobotArcade(0.0, 0.0);
    }
}
