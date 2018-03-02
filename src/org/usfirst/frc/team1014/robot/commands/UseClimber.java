package org.usfirst.frc.team1014.robot.commands;

import org.usfirst.frc.team1014.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class UseClimber extends Command {

	Climber climber;
	XboxController controller;

	public UseClimber(XboxController controller, Climber climber) {
		this.climber = climber;
		this.controller = controller;
	}

	protected void execute() {
		climber.climb(controller.getY(Hand.kLeft));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
