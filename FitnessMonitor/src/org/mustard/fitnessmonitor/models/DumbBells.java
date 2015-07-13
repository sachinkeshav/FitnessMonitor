package org.mustard.fitnessmonitor.models;

public class DumbBells extends Physical {
	public DumbBells() {
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.DUMBBELLS;
	}

	@Override
	public double calculateCalories() {

		return super.weight * (this.getDumbbellWeight() / this.getRepetition()) * 0.2 / 2 * 0.57;

	}

}
