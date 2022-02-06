package com.uocparking;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class UocParkManager implements CarParkManager {
	//private Vehicle[] vehicleParkingSlots = new Vehicle[20];//parking space array to store vehicle objects
	static Scanner input = new Scanner(System.in);
	//private Vehicle lastEntry = null;//to find the last entry of the vehicle which was entered

	//private ArrayList<Integer> vehicleOrderList = new ArrayList<Integer>();//is used to have the index of the vehicles
	//which are currently parked in the last in First out approach
	//private ArrayList<Vehicle> deletedTempVehicleList = new ArrayList<Vehicle>();//stores the vehicle object which had left the parking space

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
//---------sanjana -------

}
