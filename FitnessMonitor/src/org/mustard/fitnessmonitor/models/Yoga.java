package org.mustard.fitnessmonitor.models;

public class Yoga extends Physical {

	public Yoga() {
	}

	@Override
	public ActivityType activityType() {
		return null;
	}

	@Override
	public double calculateCalories() {
		return super.weight * super.getLevel() / 2 * 0.7 * super.getTime();
	}
}
