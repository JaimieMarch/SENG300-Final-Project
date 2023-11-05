package com.thelocalmarketplace.software;

import java.math.BigDecimal;
import java.util.ArrayList;
import com.jjjwelectronics.Item;
import com.jjjwelectronics.scale.AbstractElectronicScale;
import com.jjjwelectronics.scale.ElectronicScale;
import com.jjjwelectronics.scanner.BarcodedItem;
import com.thelocalmarketplace.hardware.BarcodedProduct;

import ca.ucalgary.seng300.simulation.InvalidArgumentSimulationException;
import ca.ucalgary.seng300.simulation.NullPointerSimulationException;
import powerutility.NoPowerException;
import com.thelocalmarketplace.software.ApplicationContext;
import com.thelocalmarketplace.software.SelfCheckOutStationScanner;


public class WeightDiscrepency extends ElectronicScale{
	private ElectronicScale scale;
    private double expectedWeight;

    	
    public WeightDiscrepency(double expectedWeight) {
        this.expectedWeight = expectedWeight;
        this.scale = new ElectronicScale();
        }

    public void addBarcodedProduct(BarcodedProduct product) {
    	Product product = new Product(product.getExpectedWeight());
    	
    	try {
    		scale.addProduct(product);
    		
            if (!isWeightAsExpected()) {
                blockStation();
                signalCustomer("Weight discrepancy detected");
                signalAttendant("Weight discrepancy detected. Please assist the customer.");
            }
        } catch (NoPowerException | NullPointerSimulationException | InvalidArgumentSimulationException e) {
        	
        }
    }

    private boolean isWeightAsExpected() {
        double currentWeight = currentMass.getValue().doubleValue(); // Convert Mass to double
        return currentWeight == expectedWeight;
    }

    private void blockStation() {
    	ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.blockScanner(); // Block the scanner
        applicationContext.blockBaggingArea(); //Block bagging area
    }

    private void unblockStation() {
     	ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.unBlock(); // unBlock the scanner and bagging area
    }

    private void signalCustomer(String message) {
        if (message.contains("add or remove an item")) {
            // Handle customer request to adjust the weight
            customerAdjustWeight(0.0); // pass the adjustment as needed
        } else if (message.contains("do not place item in bagging area")) {
            // Handle customer's "do not bag" request
            customerDoNotBagRequest();
        }
    }
    
    public void customerAdjustWeight(double adjustment) {
        expectedWeight += adjustment;
    }
    
    public void customerDoNotBagRequest() {
        signalCustomer("Customer: Do not place item in bagging area.");
    }

    private void signalAttendant(String message) {
        System.out.println("System to Attendant: " + message);
    }
    
   
}
