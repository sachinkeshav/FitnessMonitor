package org.mustard.fitnessmonitor.models;

public class Running extends Physical {
	private double level;
	private int time;

	public Running() {
	}

	public Running(double distance, double weight, double level, int time) throws NegativeValueException {
		super(distance, weight);
		this.level = level;
		this.time = time;
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.RUNNING;
	}

	@Override
	public double calculateCalories() {
		if (this.getTime() ==0) {
			return super.calculateCalories() * level / 2 * 0.72;
		} else
			return super.getWeight() * level / 2 * 0.57*this.getTime();
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

}
