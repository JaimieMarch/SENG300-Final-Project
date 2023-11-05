package com.thelocalmarketplace.software;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.scale.AbstractElectronicScale;
import com.jjjwelectronics.scale.ElectronicScale;
import com.jjjwelectronics.scanner.BarcodedItem;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.jjjwelectronics.Item;
import com.jjjwelectronics.Mass;
import ca.ucalgary.seng300.simulation.InvalidArgumentSimulationException;
import ca.ucalgary.seng300.simulation.NullPointerSimulationException;
import powerutility.NoPowerException;
import com.thelocalmarketplace.software.ApplicationContext;
import com.thelocalmarketplace.software.SelfCheckOutStationScanner;


public class WeightDiscrepency extends ElectronicScale{
    private double expectedWeight;

    	
    public WeightDiscrepency(double expectedWeight) {
        this.expectedWeight = expectedWeight;
        }
    
//    public double getExpectedWeight(BarcodedProduct product) {
//    	return product.getExpectedWeight();
//
//    }
//
//    public class NewItem extends Item {
//        public NewItem(Mass mass) {
//            super(mass);
//        }
//    }
//
//    public void addBarcodedproduct(BarcodedProduct product) {
//    	double productWeight = getExpectedWeight(product);
//    	Mass productMass = new Mass(productWeight);
//        Item item = new NewItem(productMass);
//
//
//    	try {
//    		addAnItem(item);
//
//            if (!isWeightAsExpected(productWeight)) {
//                blockStation();
//                signalCustomer("Weight discrepancy detected");
//                signalAttendant("Weight discrepancy detected. Please assist the customer.");
//            }
//        } catch (NoPowerException | NullPointerSimulationException | InvalidArgumentSimulationException e) {
//
//        }
//    }
//
//    private boolean isWeightAsExpected(double productWeight) throws OverloadedDevice {
//    	double currentWeight = getCurrentMassOnTheScale().getMassValue();
//        return currentWeight == expectedWeight;
//    }
//
//    private void blockStation() {
//    	ApplicationContext applicationContext = new ApplicationContext();
//        applicationContext.blockScanner(); // Block the scanner
//        applicationContext.blockBaggingArea(); //Block bagging area
//    }
//
//    private void unblockStation() {
//     	ApplicationContext applicationContext = new ApplicationContext();
//        applicationContext.unBlock(); // unBlock the scanner and bagging area
//    }
//
//    private void signalCustomer(String message) {
//        if (message.contains("add or remove an item")) {
//            // Handle customer request to adjust the weight
//            customerAdjustWeight(0.0); // pass the adjustment as needed
//            unblockStation();
//        } else if (message.contains("do not place item in bagging area")) {
//            // Handle customer's "do not bag" request
//            customerDoNotBagRequest();
//            unblockStation();
//        }
//    }
//
//    public void customerAdjustWeight(double adjustment) {
//        expectedWeight += adjustment;
//    }
//
//    public void customerDoNotBagRequest() {
//        signalCustomer("Customer: Do not place item in bagging area.");
//    }
//
//    private void signalAttendant(String message) {
//        System.out.println("System to Attendant: " + message);
//        unblockStation();
//    }
   
}
