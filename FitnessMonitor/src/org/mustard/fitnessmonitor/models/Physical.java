package org.mustard.fitnessmonitor.models;

public abstract class Physical {

	private double distance;
	public static double weight;
	private double time;
	private int level;
	double dumbbellWeight;
	private int repetition;
	double benchPressWeight;

	public double getBenchPressWeight() {
		return benchPressWeight;
	}

	public void setBenchPressWeight(double benchPressWeight) {
		this.benchPressWeight = benchPressWeight;
	}

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public double getDumbbellWeight() {
		return dumbbellWeight;
	}

	public void setDumbbellWeight(double dumbbellWeight) {
		this.dumbbellWeight = dumbbellWeight;
	}

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
}
