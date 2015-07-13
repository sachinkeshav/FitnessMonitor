package org.mustard.fitnessmonitor.models;

public class PullUp extends Physical {

	int repetition;

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public PullUp() {
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.PULLUP;
	}

	@Override
	public double calculateCalories() {
		return super.weight * 0.57*this.repetition;

	}

}
