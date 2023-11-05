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
