package com.uocparking;

public class Car extends Vehicle{
	private int noOfDoors;
	private String carColor;
	private String carShade;
	
	public Car(String id, String brand, DateTime entryTime, int noOfDoors, String carColor, String carShade) {
		super(id, brand, entryTime);
		this.noOfDoors = noOfDoors;
		this.carColor = carColor;
		this.carShade = carShade;
	}

	public Car(String id, String brand, DateTime entryTime, int noOfDoors, String color) {
		super(id, brand, entryTime);
	}

	public String getCarShade() {
		return carShade;
	}

	public void setCarShade(String carShade) {
		this.carShade = carShade;
	}	

	public String getNoOfDoors() {
		return String.valueOf(noOfDoors);
	}
	public void setNoOfDoors(int noOfDoors) {
		this.noOfDoors = noOfDoors;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
}
