package org.usfirst.frc.team1014.robot.commands.auto;

import org.usfirst.frc.team1014.robot.commands.core.DriveStraight;
import org.usfirst.frc.team1014.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1014.robot.subsystems.Grabber;
import org.usfirst.frc.team1014.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMoveCloseScale extends CommandGroup{

	public AutoMoveCloseScale(Drivetrain driveTrain, Lifter lifter, Grabber grabber) {
		this.addSequential(new AutoRaiseScale(lifter, 1));
		this.addSequential(new DriveStraight(driveTrain, .3, .9)); 
		this.addSequential(new AutoRelease(grabber));
		this.addSequential(new DriveStraight(driveTrain, -.3, 1)); 
		this.addSequential(new AutoRaiseScale(lifter, -1));


	}
}
