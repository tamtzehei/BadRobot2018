package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1014.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightDistance extends Command {
	private Drivetrain driveTrain;
	private Grabber grabber;
	private double seconds;
	private double speed;

	long startLastGrab = 0;

	long startTime;

	double linRegOffset = -2.4788;
	double linRegScaler = 56.4918;
	
	/**
	 * 
	 * @param driveTrain 
	 * @param distance - distance in inches
	 */

	public DriveStraightDistance(Drivetrain driveTrain, double distance) {
		this.speed = .5;

		// Inverse of graph from linear regression
		this.seconds = (distance - linRegOffset) / linRegScaler;
		requires(driveTrain);
		this.driveTrain = driveTrain;
		this.grabber = grabber;
	}

	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		driveTrain.driveStraight(speed);
		grabber.heartBeat(System.currentTimeMillis());
	}
	

	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - startTime) > (int) (1000d * seconds);
	}

	@Override
	protected void end() {
		driveTrain.directDrive(0, 0);
	}

}
