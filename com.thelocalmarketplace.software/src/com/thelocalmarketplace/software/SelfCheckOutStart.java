package com.thelocalmarketplace.software;

import java.util.Scanner;

import com.thelocalmarketplace.hardware.SelfCheckoutStation;
import com.thelocalmarketplace.software.SelfCheckOutStationScanner;

import powerutility.PowerGrid;

//Start a self check out software
public class SelfCheckOutStart extends SelfCheckOutStationScanner {

    @Override
    // Initialize the self-checkout application
    public ApplicationContext initApplication() {
    	
    	// Construct and initialize the self-checkout station and application context
        SelfCheckoutStation checkoutStation = new SelfCheckoutStation();
        ApplicationContext context = new ApplicationContext(checkoutStation);
        
        // Register the barcode scanner listener with the scanner
        checkoutStation.scanner.register(new BarcodeScannerListenerImpl(context));

        // Register the bagging area listener with the bagging area
        checkoutStation.baggingArea.register(new BaggingAreaListenerImpl(context));

        return context;
    }
   
    public class main{
    	public main(String[] args) {
    	//initialization of 5 key components
    	SelfCheckoutStation scs = new SelfCheckoutStation();
    	ApplicationContext ac = new ApplicationContext(scs);
    	PowerGrid pg = PowerGrid.instance();
    	PayWithCoin pc = new PayWithCoin();
    	WeightDiscrepancy wd = new WeightDiscrepancy();
    	
    	//Plugging in and then turning on this instance of the station
    	ac.plugIn(pg);
    	ac.turnOn();
    	
    	//initializing the database for the program
    	DatabaseSeeder.seedDatabase();
    	
    	//displays a start text and prompts user to hit enter before continuing
    	main.StartSession();
    	
    	//displays the options available and stores the option given by user
    	//into a string "select" that can be used to continue further.
    	main.OptionText();
    	
  
    }

		public void Status() {
        	public static boolean sessionStarted = false;
    	
        private static void StartSession() {
        	Scanner scanner = new Scanner(System.in);
        	if(sessionStarted == true) {
        		return;
        	}
        	sessionStarted = true;
        	System.out.println("Welcome to self checkout!");
        	System.out.println("Enter 1 to begin");
        	scanner.nextLine();
        	System.out.println("Session Starting!");
        	scanner.close();
        	
        }
    	private static void OptionText() {
    		Scanner scanner = new Scanner(System.in);
    		String select;
    		System.out.println("Press 1 to add an item");
    		System.out.println("Press 2 to remove an item");
    		System.out.println("Press 3 to pay");
    		System.out.println("Press 4 to add your own bag");
    		System.out.println("Press 5 to select a language");
    		System.out.println("Press 9 to request an attendant");
    		select = scanner.nextLine();
    		scanner.close();
		}
    }
}
