package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.commands.core.DriveStraightDistance;
import org.usfirst.frc.team1014.robot.commands.core.Spin;
import org.usfirst.frc.team1014.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1014.robot.subsystems.Grabber;
import org.usfirst.frc.team1014.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoScaleFromCenter extends CommandGroup{
	/**
	 * 
	 * @param driveTrain
	 * @param direction - -1 for right, 1 for left
	 */
	public AutoScaleFromCenter(Drivetrain driveTrain, Lifter lifter, Grabber grabber, int direction) {	//untested
		this.addSequential(new AutoCenterAB(driveTrain, direction));
		this.addSequential(new DriveStraightDistance(driveTrain, 100));		//change so approach is from 45 degrees
		this.addSequential(new Spin(driveTrain, direction * 45));
		this.addSequential(new AutoMoveCloseScale(driveTrain, lifter, grabber));


	}
	
}
