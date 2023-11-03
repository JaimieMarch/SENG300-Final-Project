package com.thelocalmarketplace.software;

import com.thelocalmarketplace.hardware.SelfCheckoutStation;

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

    public static void main(String[] args) throws Exception {
    	
    	// Seed the database with initial product data
        DatabaseSeeder.seedDatabase();
        
        SelfCheckOutStart selfCheckOutStart = new SelfCheckOutStart();

        // Start the self-checkout process
        selfCheckOutStart.start(null);

        // Add barcoded products
        selfCheckOutStart.addProduct(null);
    }
}

