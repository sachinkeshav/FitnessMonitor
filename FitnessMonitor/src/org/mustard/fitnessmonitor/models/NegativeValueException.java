package org.mustard.fitnessmonitor.models;

public class NegativeValueException extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public NegativeValueException(){
	super();
}

public NegativeValueException(String message){
	super(message);
}
}
