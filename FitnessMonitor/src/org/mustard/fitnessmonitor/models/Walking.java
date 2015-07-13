package org.mustard.fitnessmonitor.models;

public class Walking extends Physical {
	public Walking() {
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.WALKING;
	}

	@Override
	public double calculateCalories() {
		if (super.getTime() == 0) {
			return super.calculateCalories() * super.getLevel() / 2 * 0.57;
		} else
			return super.weight * super.getLevel() / 2 * 0.3 * super.getTime();
	}

}
