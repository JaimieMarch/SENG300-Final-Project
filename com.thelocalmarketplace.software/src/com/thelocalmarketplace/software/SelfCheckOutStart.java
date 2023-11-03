package com.thelocalmarketplace.software;

import com.thelocalmarketplace.hardware.SelfCheckoutStation;

//Start a self check out software
public class SelfCheckOutStart extends SelfCheckOutStationScanner {

    @Override
    // Initialize the self-checkout application
    public ApplicationContext initApplication() {
        // Construct the self-checkout station
        SelfCheckoutStation checkoutStation = new SelfCheckoutStation();

        // Initialize the application context with the station
        ApplicationContext context = new ApplicationContext(checkoutStation);

        // Register the barcode scanner listener with the scanner
        checkoutStation.scanner.register(new BarcodeScannerListenerImpl(context));

        // Register the bagging area listener with the bagging area
        checkoutStation.baggingArea.register(new BaggingAreaListenerImpl(context));

        // Return the initialized application context
        return context;
    }

    public static void main(String[] args) throws Exception {
        SelfCheckOutStart selfCheckOutStart = new SelfCheckOutStart();

        // Start the self-checkout process
        selfCheckOutStart.start(null);

        // Add barcoded products
        selfCheckOutStart.addProduct(null);
    }
}

