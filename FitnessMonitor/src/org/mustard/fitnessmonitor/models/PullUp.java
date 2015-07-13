package org.mustard.fitnessmonitor.models;

public class Swimming extends Physical {

	public Swimming() {
	}

	@Override
	public ActivityType activityType() {
		return ActivityType.SWIMMING;
	}

	@Override
	public double calculateCalories() {
		if(super.getTime()==0){
			return super.calculateCalories()*0.72;
		}
		return super.weight * 0.57 * super.getTime();
		
	}

}
