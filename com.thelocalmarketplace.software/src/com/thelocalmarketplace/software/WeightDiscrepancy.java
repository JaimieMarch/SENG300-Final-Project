package com.thelocalmarketplace.software;

import java.math.BigDecimal;
import java.util.ArrayList;
import com.jjjwelectronics.Item;
import com.jjjwelectronics.scale.AbstractElectronicScale;
import com.jjjwelectronics.scale.ElectronicScale;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import ca.ucalgary.seng300.simulation.InvalidArgumentSimulationException;
import ca.ucalgary.seng300.simulation.NullPointerSimulationException;
import powerutility.NoPowerException;


public class WeightDiscrepancy extends ElectronicScale{
	private ElectronicScale scale;
    private double expectedWeight;
    private boolean isStationBlocked = false; // Flag to indicate station blocking

    public WeightDiscrepancy(double expectedWeight) {
        this.expectedWeight = expectedWeight;
        this.scale = new ElectronicScale();
        }
    

    public void addBarcodedProduct(BarcodedProduct product) {
        Item item = new Item(product.getExpectedWeight()); // Create an item from the product

        try {
            scale.addAnItem(item);

            if (!isWeightAsExpected()) {
                blockStation();
                signalCustomer("Weight discrepancy detected");
                signalAttendant("Weight discrepancy detected. Please assist the customer.");
                if (isStationBlocked()) {
                    handleAttendantResponse();
                }
            }
        } catch (NoPowerException | NullPointerSimulationException | InvalidArgumentSimulationException e) {
        }
    }
    
    public void attendantApproveWeightDiscrepancy() {
        if (isStationBlocked()) {
            handleAttendantResponse();
        }
    }
    
    private void handleAttendantResponse() {
        signalAttendant("Attendant notified. Fix this issue.");
        isStationBlocked = false;// Unblocks the station after signaling the attendant
    }

    private boolean isWeightAsExpected() {
        double currentWeight = currentMass.getValue().doubleValue(); // Convert Mass to double
        return currentWeight == expectedWeight;
    }

    private void blockStation() {
        isStationBlocked = true;
    }

    public boolean isStationBlocked() {
        return isStationBlocked;
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