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
    	SelfCheckoutStation scs = new SelfCheckoutStation();
    	ApplicationContext ac = new ApplicationContext(scs);
    	PowerGrid pg = PowerGrid.instance();
    	PayWithCoin pc = new PayWithCoin();
    	WeightDiscrepancy wd = new WeightDiscrepancy();
    	
    	
    	ac.plugIn(pg);
    	ac.turnOn();
    	
    	DatabaseSeeder.seedDatabase();
    	
    	main.StartSession();
    	
    	
    	
  
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
    }
}
