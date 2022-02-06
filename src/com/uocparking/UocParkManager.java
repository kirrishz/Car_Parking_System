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
	}
		public int checkForFreeSlot(String VehicleType) {
			for (int i = 0; i < 20; i++) { // iterating through each slot to find a
				// free spot
				if (vehicleParkingSlots[i] == null) {
					if (VehicleType.equalsIgnoreCase("van")) { // if its a van need
						// to check whether
						// adjacent slot is
						// also free
						if (vehicleParkingSlots[i + 1] == null) {
							return i;
						}
					} else { // since one slot is sufficient for cars and bikes
						return i;
					}
				}
			}
			return -1; // if there is no free slots
		}

		public int totalOfSlots() {
			int number = 0;
			for (int i = 0; i < 20; i++) {
				if (vehicleParkingSlots[i] == null) {
					++number;
				}
			}
			return number;
		}
		public void deleteVehicle() {
			boolean foundFlag = false;
			int i;
			System.out.print("Enter Plate ID number of the vehicle to be deleted: ");
			String id = input.next();

			for (i = 0; i < 20; i++) { // loop to find the element index
				if (vehicleParkingSlots[i] != null) { // to check whether an object
					// is there (To avoid
					// nullPointerException)
					if (vehicleParkingSlots[i].getPlateID().equalsIgnoreCase(id)) {
						foundFlag = true;
						break; // end for loop once the value is found
					}
				}
			}
			if (!foundFlag) {
				System.err.println("**Invalid Vehicle Plate ID, Please Try Again**"); 
				return;
			}
			// to display the vehicle leaving
			String VehicleType = vehicleParkingSlots[i].getClass().getSimpleName();
			System.out.println("A " + VehicleType + " left the parking space.");
			deletedTempVehicleList.add(vehicleParkingSlots[i]);
			if (VehicleType.equalsIgnoreCase("van")) {
				// to physically remove the element from the vehicle array
				vehicleParkingSlots[i] = null;
				vehicleParkingSlots[i + 1] = null;
			} else {
				vehicleParkingSlots[i] = null;
			}
			vehicleOrderList.remove(i);

		}
		public void printStatistics() {
			printVehiclePercentage(); // method call of the method which prints
			// vehicle percentage
			printFirstAndLastVehicle();
		}

		private void printVehiclePercentage() {
			int car = 0, van = 0, bike = 0, total = 0;
			String vehicleType;
			for (int i = 0; i < 20; i++) { // loop to find the element index
				if (vehicleParkingSlots[i] != null) { // if an element is not empty
					vehicleType = vehicleParkingSlots[i].getClass().getSimpleName();
					++total;
					switch (vehicleType) { // to increment each vehicle type counter
						case "Car":
							++car;
							break;
						case "Van":
							++van;
							++i; // to skip the next slot as well since a van occupied 2
							// slots
							break;
						case "Motorbike":
							++bike;
							break;
					}
				}
			}
			// Percentage calculation
			int carPercentage, vanPercentage, bikePercentage;
			if (total == 0) { // if carpark is empty(to avoid arithmeticException)
				carPercentage = 0;
				vanPercentage = 0;
				bikePercentage = 0;
			} else {
				carPercentage = (car * 100 / total);
				vanPercentage = (van * 100 / total);
				bikePercentage = (bike * 100 / total);
			}

			System.out.println("Currently Parked Vehicle percentage");
			System.out.println("------------------------------------");
			System.out.println("         CAR : " + carPercentage + "%");
			System.out.println("         VAN : " + vanPercentage + "%");
			System.out.println("         BIKE:" + bikePercentage + "%");
			System.out.println("");
		}

		private void printFirstAndLastVehicle() {
			// to find the vehicle that was parked first.
			if (vehicleOrderList.size() != 0) {
				System.out.println("First vehicle Which was parked");
				System.out.println("-------------------------------");
				System.out.println("ID : " + vehicleParkingSlots[vehicleOrderList.get(0)].getPlateID());
				System.out.println("Type : " + vehicleParkingSlots[vehicleOrderList.get(0)].getClass().getSimpleName());
				System.out.println("Entry time : " + vehicleParkingSlots[vehicleOrderList.get(0)].getEntryTime());
			} else {
				System.out.println("No vehicle in the parking currently");
			}

			// to find the last vehicle that entered the parking slot.
			if (lastEntry != null) {
				System.out.println("Last vehicle which was parked");
				System.out.println("------------------------------");
				System.out.println("ID : " + lastEntry.getPlateID());
				System.out.println("Type : " + lastEntry.getClass().getSimpleName());
				System.out.println("Entry time : " + lastEntry.getEntryTime());
			} else {
				System.out.println("**The Parking space is Empty no vehicles are parked currently**");
			}

		}
}
