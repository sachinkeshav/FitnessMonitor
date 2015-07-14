package org.mustard.fitnessmonitor.models;

public class PullUp extends Physical {

	public PullUp() {
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.PULLUP;
	}

	@Override
	public double calculateCalories() {
		return super.weight * 0.57 * super.getRepetition();

	}

}
