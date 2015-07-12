package org.mustard.fitnessmonitor.models;

public class Cycling extends Physical {

	private double time;

	public double getAge() {
		return time;
	}

	public void setAge(double age) {
		this.time = age;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public double level;

	public Cycling(double distance, double weight, double age, double level) throws NegativeValueException {
		super(distance, weight);
		this.time = age;
		this.level = level;
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.CYCLING;
	}

	@Override
	public double calculateCalories() {
		if (this.getTime() ==0) {
			return super.calculateCalories() * level / 2 * 0.72;
		} else
			return super.calculateCalories() * level / 2 * 0.57*this.getTime();
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
	
}
