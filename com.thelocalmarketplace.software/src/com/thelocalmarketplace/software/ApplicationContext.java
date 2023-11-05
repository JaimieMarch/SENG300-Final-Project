package com.thelocalmarketplace.software;


//        Ananya, Jain: 30196069
//        Emily, Williams: 30122865
//        Jaimie, Marchuk: 30112841
//        Kenny, ZENG: 30151985
//        Yang, YANG: 30156356

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.jjjwelectronics.AbstractDevice;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.SelfCheckoutStation;
import com.thelocalmarketplace.hardware.external.ProductDatabases;

import powerutility.PowerGrid;

public class ApplicationContext extends AbstractDevice {

    // The current application points to the self-checkout station's physical machine
    public SelfCheckoutStation checkoutStation;

    // Hashmap to keep track of the status of devices
    public final HashMap<Object, DeviceStatus> deviceStatusHashMap = new HashMap<>();

    // Set of products that have been added
    private Set<BarcodedProduct> productSet = new HashSet<>();

    // The product that is currently being added
    private BarcodedProduct currentProduct;

    // Singleton pattern to ensure there is a physical machine
    public ApplicationContext() {
    }

    public ApplicationContext(SelfCheckoutStation checkoutStation) {
    	// Seed the database with initial product data
        DatabaseSeeder.seedDatabase();

        this.checkoutStation = checkoutStation;
        
        // Set status of the check out station
        this.deviceStatusHashMap.put(checkoutStation, DeviceStatus.UN_START);

        // Set the status of the bagging area
        this.deviceStatusHashMap.put(checkoutStation.baggingArea, DeviceStatus.UN_START);

        // Set the status of the scanning machine
        this.deviceStatusHashMap.put(checkoutStation.scanner, DeviceStatus.UN_START);
    }

    @Override
    public synchronized void plugIn(PowerGrid grid) {
        // Change the status to STARTING when charging
        this.deviceStatusHashMap.forEach((device, status) -> {
            if (status == DeviceStatus.UN_START) {
                this.deviceStatusHashMap.put(device, DeviceStatus.START);
            }
        });
        this.checkoutStation.plugIn(grid);
    }

    @Override
    public synchronized void turnOn() {
        // Turn on the self-checkout station
        this.checkoutStation.turnOn();
    }

    // Set the device status of the checkout station
    public void setCheckoutStationDeviceStatus(DeviceStatus status) {
        this.deviceStatusHashMap.put(this.checkoutStation, status);
    }

    // Change the status of a device
    public void setDeviceStatus(Object device, DeviceStatus status) {
        this.deviceStatusHashMap.put(device, status);
    }

    // Store scanned product information
    public void store(BarcodedProduct product) {
    	// Verify the product exists in the database before storing
        if (ProductDatabases.BARCODED_PRODUCT_DATABASE.containsKey(product.getBarcode())) {
            this.currentProduct = product;
        } else {
            // Handle the case where the product does not exist in the database
            // For example, you might log an error or display a message to the user
            System.out.println("Product does not exist in the database.");
        }
    }
    
    public double calculateTotalCost(BarcodedProduct product) 
    {
        double totalCost = 0.0;
        double price = product.getPrice(); 
        
        totalCost += price; 
        
        return totalCost;
    }

    // Verify the characteristics of a product
    public boolean verify(BarcodedProduct barcodedProduct) {
        return this.currentProduct != null && this.currentProduct.equals(barcodedProduct);
    }

    // Prevent interaction with the self-checkout station and scanner
    public void blockScanner() {
        this.setCheckoutStationDeviceStatus(DeviceStatus.UNABLE);
        this.setDeviceStatus(this.checkoutStation.scanner, DeviceStatus.UNABLE);
    }

    // Disable the bagging area
    public void blockBaggingArea() {
        this.setDeviceStatus(this.checkoutStation.baggingArea, DeviceStatus.UNABLE);
    }

    // Allow interaction
    public void unBlock() {
        // Modify the software's stored products
        this.productSet.add(this.currentProduct);
        this.currentProduct = null;
        this.setDeviceStatus(this.checkoutStation.scanner, DeviceStatus.ABLE);
        this.setDeviceStatus(this.checkoutStation.baggingArea, DeviceStatus.ABLE);
        this.setCheckoutStationDeviceStatus(DeviceStatus.ABLE);
    }
    

}

