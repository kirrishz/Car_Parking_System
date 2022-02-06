package com.uocparking;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class UocParkManager implements CarParkManager {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println(" ---------------------------------------------");
		System.out.println(" |********-Car Park Management System-********|");
		System.out.println(" |********-UOC FOT-********|");
		System.out.println(" |********-IA2211 Assignment -********|");
		System.out.println(" |********-Team 2019t00356 2019t00381 2019t00362 -********|");
		System.out.println(" ---------------------------------------------\n");
		displayLogin();//redirected to the displayLogin method
	}

	public static void displayLogin() {
		System.out.println("\n****** Login Menu ******");
		System.out.println("\n****** guide username : adminuoc password: adminuoc ******");
		Scanner loginInput = new Scanner(System.in);
		System.out.print("Enter Username: ");
		String userName = loginInput.next();
		System.out.print("Enter Password: ");
		String password = loginInput.next();
		if (userName.equals("adminuoc") && password.equals("adminuoc")) {// if userName and password equals
			// then display menu
			displayMainMenu();// directed to display menu method
		} else {// else display error message
			System.out.println("\n#Incorrect Username or Password, Please Try Again#");
			displayLogin();// login menu prompted again
		}
		loginInput.close();//close the scanner
	}

	public static void displayMainMenu() {
		while (true) {//infinity loop
			System.out.println("");
			System.out.println("1. Add a vehicle to the parking Space");
			System.out.println("2. Delete a vehicle from the parking Space");
			System.out.println("3. Display all vehicles parked currently in the parking space");
			System.out.println("4. Display statistics of the car park");
			System.out.println("5. Display vehicles of a specific Day");
			System.out.println("Press Q to Quit the Program.");
			System.out.print("Enter Selection: ");

			String userInput = input.next();// prompted for input and stored in
											// the userInput variable
			System.out.println();

			switch (userInput.toLowerCase()) {
			case "1":
				//carParkObject.addVehicle();
				break;
			case "2":
				//carParkObject.deleteVehicle();
				break;
			case "3":
				//carParkObject.displayCurrentList();
				break;
			case "4":
				//carParkObject.printStatistics();
				break;
			case "5":
				//carParkObject.displayPerDayList();
				break;
			case "q":
				System.exit(0);// terminates the program
				break;
			default:
				System.err.println("\n**Please, Enter a Valid Input**");
				System.out.println();
			}
		}
	}

	public void addVehicle() {
		boolean typeVal = false;
		String vehicleType = ""; // String variable to hold the vehicle type
		while (!typeVal) { // loop to make sure only valid type is being entered
			System.out.print("Enter vehicle type(car/bike/van): ");
			vehicleType = input.next();
			if (vehicleType.equalsIgnoreCase("car") || vehicleType.equalsIgnoreCase("van")
					|| vehicleType.equalsIgnoreCase("bike")) {
				typeVal = true;
			} else {
				System.err.println("\n**Please, Enter a Valid Input**");
				System.out.println("");
			}
		}

		int checkFreeSpace = checkForFreeSlot(vehicleType);
		if (checkFreeSpace == -1) {
			System.out.println("**Parking Slot Full, No free slot available!**");
			return;
		}

		System.out.print("Enter Vehicle Plate ID number: ");
		String id = input.next();
		System.out.print("Enter Vehicle brand name: ");
		String brand = input.next();
		int hours, mins, date, month, year;

		do {
			System.out.print(
					"Enter entry time/date (HourHour(HH) MinsMins(MM) DayDay(DD) MonthMonth(MM) YearYearYearYear(YYYY): ");
			hours = input.nextInt();
			mins = input.nextInt();
			date = input.nextInt();
			month = input.nextInt();
			year = input.nextInt();
		} while (hours < 0 || hours > 24 || mins < 0 || mins > 60 || date < 0 || date > 31 || month < 0 || month > 12
				|| year != 2022);
		// validate the date and time
		DateTime entryTime = new DateTime(hours, mins, date, month, year);
		vehicleOrderList.add(checkFreeSpace);

		switch (vehicleType) {
			case "car":
				System.out.print("Enter no of doors: ");
				while (!input.hasNextInt()) {
					System.err.print("Please, Enter a integer value for No of Doors : ");
					input.next();// validating user input based on string value
				}
				int noOfDoors = input.nextInt();
				System.out.print("Enter car color: ");
				String color = input.next();
				vehicleParkingSlots[checkFreeSpace] = new Car(id, brand, entryTime, noOfDoors, color);
				break;
			case "van":
				System.out.print("Enter cargo volume: ");
				while (!input.hasNextDouble()) {
					System.err.print("Please, Enter a integer value for Cargo Volume : ");
					input.next();// validating user input based on string value
				}
				double volume = input.nextDouble();
				vehicleParkingSlots[checkFreeSpace] = new Van(id, brand, entryTime, volume);
				vehicleParkingSlots[checkFreeSpace + 1] = new Van(id, brand, entryTime, volume);
				break;
			case "bike":
				System.out.print("Enter engine size: ");
				while (!input.hasNextInt()) {
					System.err.print("Please, Enter a integer value for engine size : ");
					input.next();// validating user input based on string value
				}
				int size = input.nextInt();
				vehicleParkingSlots[checkFreeSpace] = new MotorBike(id, brand, entryTime, size);
				break;
		}
		lastEntry = vehicleParkingSlots[checkFreeSpace];
		System.out.println("");
		System.out.println("Vehicle parked Sucessfully!");
		System.out.println("No of free slots remaining is " + totalOfSlots());
		//_______
}
