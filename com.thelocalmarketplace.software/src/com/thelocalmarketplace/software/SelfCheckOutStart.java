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
   
    public void main(String[] args) {
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
    	Status();
    	
    }

	public void Status() {
        boolean sessionStarted = false;

        Scanner scanner = new Scanner(System.in);
       	if(sessionStarted == true) {
       		scanner.close();
       		return;
       	}
       	sessionStarted = true;
       	System.out.println("Welcome to self checkout!");
       	System.out.println("Enter 1 to begin");
       	scanner.nextLine();
       	System.out.println("Session Starting!");
       	scanner.close();
    }
}
