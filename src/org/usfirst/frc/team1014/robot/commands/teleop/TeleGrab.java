package org.usfirst.frc.team1014.robot.commands.teleop;

import org.usfirst.frc.team1014.robot.subsystems.Grabber;
import org.usfirst.frc.team1014.robot.subsystems.Lifter;
import org.usfirst.frc.team1014.robot.util.LogUtil;

import badlog.lib.BadLog;
import badlog.lib.DataInferMode;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class TeleGrab extends Command {
	private Joystick controller;
	private Grabber grabber;
	private Lifter lifter;

	long startLastGrab = 0;

	boolean autoGrab = false;
	private static final int AUTO_GRAB_LIFT_TIME = 450;
	long autoGrabLiftUntil = 0;

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void execute() {
		if (controller.getRawButton(4)) {
			// Autograb
			grabber.turnCollect(1);
			lifter.move(-.8, false);
			autoGrab = true;
		} else {
			boolean forceLift = false;
			if (autoGrab) {
				autoGrab = false;
				// Auto grab ended
				autoGrabLiftUntil = System.currentTimeMillis() + AUTO_GRAB_LIFT_TIME;
			}
			forceLift = System.currentTimeMillis() - autoGrabLiftUntil < 0;

			if (controller.getRawButton(2)) {
				// Collect cubes
				grabber.turnCollect(1);
			} else if (controller.getRawButton(1)) {
				// release
				grabber.turnRelease(.6);
			} else {
				grabber.turnCollect(isGrabbing() ? .2 : 0);
				controller.setRumble(RumbleType.kLeftRumble, isGrabbing() ? 1 : 0);
				controller.setRumble(RumbleType.kRightRumble, isGrabbing() ? 1 : 0);
			}

			{
				double speed = controller.getRawAxis(1);
				
				if(Math.abs(speed) < .1)
					speed = 0;
				
				boolean overrideLimits = false;

				if (forceLift) {
					speed = 1;
				}

				int pov;
				if ((pov = controller.getPOV()) != -1) {
					if(pov < 90 || pov >= 315) {
						speed = 1;
					} else if (pov > 90 && pov < 270) {
						speed = -1;
					}
					overrideLimits = true;
				}

				lifter.move(speed, overrideLimits);
			}
		}
	}

	private boolean isGrabbing() {
		return (System.currentTimeMillis() - startLastGrab) % 1000 < 250;
	}

	public TeleGrab(Joystick controller, Grabber grabber, Lifter lifter) {
		requires(grabber);
		requires(lifter);
		this.controller = controller;
		this.grabber = grabber;
		this.lifter = lifter;

		BadLog.createTopicSubscriber("Grabber/Heartbeat", "bool", DataInferMode.DEFAULT);
	}

}
