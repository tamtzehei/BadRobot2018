package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.Robot;
import org.usfirst.frc.team1014.robot.commands.teleop.TeleDrive;
import org.usfirst.frc.team1014.robot.commands.teleop.TeleGrab;
import org.usfirst.frc.team1014.robot.subsystems.Climber;
import org.usfirst.frc.team1014.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1014.robot.subsystems.Grabber;
import org.usfirst.frc.team1014.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Teleop extends CommandGroup {
	public Teleop(Drivetrain driveTrain, Grabber grabber, Lifter lifter, Climber climber) {
		super.addParallel(new TeleDrive(driveTrain, Robot.oi.controller0, Robot.oi.controller1));
		super.addParallel(new TeleGrab(Robot.oi.controller1, grabber, lifter));
	}
}
