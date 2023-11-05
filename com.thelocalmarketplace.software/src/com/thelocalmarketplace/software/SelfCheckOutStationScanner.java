package com.thelocalmarketplace.software;

import com.jjjwelectronics.Item;
import com.jjjwelectronics.Mass;
import com.jjjwelectronics.scale.ElectronicScale;
import com.jjjwelectronics.scanner.Barcode;
import com.jjjwelectronics.scanner.BarcodeScanner;
import com.jjjwelectronics.scanner.BarcodedItem;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.Product;
import com.thelocalmarketplace.hardware.external.ProductDatabases;

import powerutility.PowerGrid;

public class SelfCheckOutStationScanner {
	
    
    // The application context for the self-checkout station
    private ApplicationContext application = null;

    // Abstract method to initialize the self-checkout station
    public ApplicationContext initApplication() {
		return null;
	}

    // Method to start the self-checkout station
    public void start(PowerGrid powerGrid){
        application = initApplication(); // Initialize the application

        application.plugIn(powerGrid); // Plug the station into the power grid

        application.turnOn(); // Turn on the station

        // Set the self-checkout station's status to ABLE after everything is set up
        application.setCheckoutStationDeviceStatus(DeviceStatus.ABLE);
    };

    public void addProduct(Product product) throws Exception {
        BarcodedItem barcodedItem = getBarcodedItem(product);

        // Verify the product exists in the database before proceeding
        if (ProductDatabases.BARCODED_PRODUCT_DATABASE.containsKey(barcodedItem.getBarcode())) {
        	scan(barcodedItem);
        	storeProduct((BarcodedProduct) product);
            addAnItem(barcodedItem);
        } else {
            System.out.println("Scanned product does not exist in the database.");
        }
        }

    private void addAnItem(Item item) throws Exception {
        ElectronicScale baggingArea = application.checkoutStation.baggingArea;
        
        // Verify if the bagging area is usable
        if(application.deviceStatusHashMap.get(baggingArea).getCode() != DeviceStatus.ABLE.getCode()){
            throw new Exception("Station is out of work");
        }
        
        application.blockBaggingArea(); // Block interaction with the bagging area
        
        baggingArea.addAnItem(item); // Place the item on the bagging area
    }

    private BarcodedItem getBarcodedItem(Product product) throws Exception {
        // This block is for barcoded products
        if(product instanceof BarcodedProduct){
            BarcodedProduct barcodedProduct = (BarcodedProduct) product; // Get product information
            Barcode barcode = barcodedProduct.getBarcode();
            BarcodedItem barcodedItem = new BarcodedItem(barcode, new Mass(barcodedProduct.getExpectedWeight()));

            return barcodedItem;
        }
        throw new Exception("Product does not conform to the expected type...");
    }

    // Method to scan the barcode
    protected void scan(BarcodedItem item) throws Exception {
        if(item == null){
            throw new Exception("Invalid parameter...");
        }
        BarcodeScanner scanner = application.checkoutStation.scanner;

        // Check if the scanner is usable
        if(application.deviceStatusHashMap.get(scanner).getCode() != DeviceStatus.ABLE.getCode()){
            throw new Exception("Scanner is unavailable...");
        }
        application.blockScanner(); // Prevent user interaction with the machine
        scanner.scan(item); // Scan the barcode
    }

    // Method to store and verify product information in the software
    private void storeProduct(BarcodedProduct product){
        application.store(product);
        double totalCost = application.calculateTotalCost(product);
        System.out.println("The total cost of products is:" + totalCost);
        
    }
}

