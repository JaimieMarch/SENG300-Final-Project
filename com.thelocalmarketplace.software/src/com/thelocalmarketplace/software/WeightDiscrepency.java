package com.thelocalmarketplace.software;

import java.math.BigDecimal;
import com.jjjwelectronics.scale.ElectronicScale;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.jjjwelectronics.Item;
import com.jjjwelectronics.Mass;
import ca.ucalgary.seng300.simulation.InvalidArgumentSimulationException;
import ca.ucalgary.seng300.simulation.NullPointerSimulationException;
import powerutility.NoPowerException;


public class WeightDiscrepency extends ElectronicScale{
    private double expectedWeight;

    	
    public WeightDiscrepency(double expectedWeight) {
        this.expectedWeight = expectedWeight;
        }
    
    public double getExpectedWeight(BarcodedProduct product) {
    	return product.getExpectedWeight();
    
    }
    
    public class NewItem extends Item {
        public NewItem(Mass mass) {
            super(mass);
        }
    }
    
    public void addBarcodedproduct(BarcodedProduct product) {
    	double productWeight = getExpectedWeight(product);
    	Mass productMass = new Mass(productWeight);
        Item item = new NewItem(productMass);

    	
    	try {
    		addAnItem(item);
    		
            if (!isWeightAsExpected(productWeight)) {
                blockStation();
                signalCustomer("Weight discrepancy detected");
                signalAttendant("Weight discrepancy detected. Please assist the customer.");
            }
        } catch (NoPowerException | NullPointerSimulationException | InvalidArgumentSimulationException e) {
        	
        }
    }

    private BigDecimal getCurrentItemMassInGrams() {
        if (items.isEmpty()) {
            return BigDecimal.ZERO; // No items on the scale
        }
        //Achieve current mass
        Item lastItem = items.get(items.size() - 1);
        Mass itemMass = lastItem.getMass();
        return itemMass.inGrams();
    }
    
    private boolean isWeightAsExpected(double productWeight) {
        if (items.isEmpty()) {
            return false; // No items on the scale, weight is not as expected
        }

        // Get the mass of the last item added to the scale in grams
        BigDecimal currentMassInGrams = getCurrentItemMassInGrams();
        double currentWeight = currentMassInGrams.doubleValue();
        //Compare masses
        return currentWeight == productWeight;
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
        unblockStation();
    }
    
   
}
