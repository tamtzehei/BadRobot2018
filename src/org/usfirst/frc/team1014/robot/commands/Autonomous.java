package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1014.robot.subsystems.Grabber;
import org.usfirst.frc.team1014.robot.subsystems.Lifter;
import org.usfirst.frc.team1014.robot.commands.core.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {	
	public Autonomous(Drivetrain driveTrain, Lifter lifter, Grabber grabber) {
		this.addSequential(new DriveStraight(driveTrain, .7, .5));
	}
}
