package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.commands.core.DriveStraightDistance;
import org.usfirst.frc.team1014.robot.commands.core.Spin;
import org.usfirst.frc.team1014.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1014.robot.subsystems.Grabber;
import org.usfirst.frc.team1014.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoExtremes extends CommandGroup{
	
	/**
	 * 
	 * @param driveTrain
	 * @param side - 1 for R to A, -1 for L to B
	 */
	public AutoExtremes(Drivetrain driveTrain, Lifter lifter, Grabber grabber, int side) { //untested
		this.addSequential(new DriveStraightDistance(driveTrain, 100));
		this.addSequential(new Spin(driveTrain, side * 90));
		this.addSequential(new DriveStraightDistance(driveTrain, 100));
		this.addSequential(new Spin(driveTrain, side * -90));
		this.addSequential(new DriveStraightDistance(driveTrain, 10));
		this.addSequential(new AutoMoveCloseScale(driveTrain, lifter, grabber));
	}
}
