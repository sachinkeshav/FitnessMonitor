package org.mustard.fitnessmonitor.models;

public class Swimming extends Physical{
	private int time;
	
	public Swimming(double distance,double weight) throws NegativeValueException{
		super(distance,weight);
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public ActivityType activityType() {
		return ActivityType.SWIMMING;
	}
	@Override
	public double calculateCalories(){
		return super.calculateCalories()*0.57*this.getTime();
	}

}
