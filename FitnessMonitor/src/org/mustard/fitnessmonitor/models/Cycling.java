package org.mustard.fitnessmonitor.models;

public class Cycling extends Physical {

	public Cycling() {
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.CYCLING;
	}

	@Override
	public double calculateCalories() {
		if (super.getTime() == 0) {
			return super.calculateCalories() * super.getLevel() / 2 * 0.72;
		} else
			return super.weight * super.getLevel()* 0.57 * (super.getTime()/2);
	}

}
