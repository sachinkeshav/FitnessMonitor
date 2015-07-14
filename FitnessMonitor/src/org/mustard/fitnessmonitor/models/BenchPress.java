package org.mustard.fitnessmonitor.models;

public class BenchPress extends Physical {

	private static final double METS = 4.5;

	public BenchPress() {
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.BENCHPRESS;
	}

	@Override
	public double calculateCalories() {
		
		int timeInHours = super.getRepetition() / 100;
		return (super.weight * 0.45) * timeInHours + 2 / super.getBenchPressWeight();
	}
}
