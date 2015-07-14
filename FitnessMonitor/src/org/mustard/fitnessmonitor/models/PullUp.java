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
		return 4* super.getRepetition()*(super.weight/100);

	}

}
