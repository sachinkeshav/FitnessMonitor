package org.mustard.fitnessmonitor.models;

public class TreadMill extends Physical {
	public TreadMill() {
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.TREADMILL;
	}

	@Override
	public double calculateCalories() {
		if (super.getTime() == 0) {
			return super.calculateCalories() * super.getLevel() / 2 * 0.72;
		} else
			return super.weight * getLevel() / 2 * 0.7 * super.getTime();
	}

}
