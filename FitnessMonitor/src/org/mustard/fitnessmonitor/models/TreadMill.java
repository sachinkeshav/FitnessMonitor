package org.mustard.fitnessmonitor.models;

public class Running extends Physical {
	public Running() {
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.RUNNING;
	}

	@Override
	public double calculateCalories() {
		if (super.getTime() == 0) {
			return super.calculateCalories() * super.getLevel() / 2 * 0.72;
		} else
			return super.weight * getLevel() / 2 * 0.7 * super.getTime();
	}

}
