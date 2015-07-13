package org.mustard.fitnessmonitor.models;

public abstract class Physical {
	private double distance;
	public static double weight;
	private double time;
	private int level;

	public Physical() {

	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	abstract public ActivityType activityType();

	public double calculateCalories() {
		return this.getDistance() * this.weight;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getWeight() {
		return 3;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
