package org.mustard.fitnessmonitor.models;

public class DumbBells extends Physical {
	private static final double METS = 4; // standard value for dumbellss
	private double timeInHours;

	public DumbBells() {
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.DUMBBELLS;
	}

	@Override
	public double calculateCalories() {
		timeInHours = super.getRepetition() / 100;

		return (super.weight * 0.45) * timeInHours + 2 / super.getDumbbellWeight();

	}

}
