package com.thelocalmarketplace.software;

import java.math.BigDecimal;
import java.util.ArrayList;
import com.jjjwelectronics.scale.ElectronicScale;
import com.jjjwelectronics.scanner.BarcodeScanner;


public class WeightDiscrepancy {
	    private boolean systemBlocked = false;
	    private boolean weightDiscrepancy = false;
	    private boolean attendantApproval = false;
	    private String customerResponse = null;
	    private double expectedWeight = 0;
	    private double currentWeight = 0;
	    private double toleranceThreshold = 0.1; // set the tolerance threshold as needed.

	    public void detectWeightChange(double currentWeight) {
	        if (!systemBlocked) {
	            this.currentWeight = currentWeight;
	            if (Math.abs(this.currentWeight - expectedWeight) > toleranceThreshold) {
	                weightDiscrepancy = true;
	                systemBlocked = true;
	                signalCustomer();
	                signalAttendant();
	            }
	        }
	    }

	    public void signalCustomer() {
	        // Notify the customer about the weight discrepancy and options
	        // to add or remove items or request assistance.
	        customerResponse = customerNotification(weightDiscrepancy);
	    }

	    public void signalAttendant() {
	        // Notify the attendant about the weight discrepancy.
	        // Attendant may choose to approve, investigate, or take action.
	        attendantNotification(weightDiscrepancy);
	    }

	    public void handleCustomerResponse(String response) {
	        if ("Add Item".equals(response) || "Remove Item".equals(response)) {
	            weightDiscrepancy = false;
	            systemBlocked = false;
	        } else if ("Do Not Bag".equals(response)) {
	            weightDiscrepancy = false;
	            systemBlocked = false;
	            expectedWeight -= itemWeight; // Assuming you have a variable itemWeight.
	        } else {
	            // Customer signaled for Attendant
	            // Handle this case.
	        }
	    }

	    public void handleAttendantResponse(String response) {
	        if ("Weight Discrepancy Approved".equals(response)) {
	            attendantApproval = true;
	            weightDiscrepancy = false;
	            systemBlocked = false;
	        } else {
	            attendantApproval = false;
	            // Perform manual investigation.
	        }
	    }

	    public void setExpectedWeight(double expectedWeight) {
	        this.expectedWeight = expectedWeight;
	    }

	    public void setToleranceThreshold(double threshold) {
	        toleranceThreshold = threshold;
	    }

	    // Notification methods need to be implemented as per your system's requirements.
	    private String customerNotification(boolean weightDiscrepancy) {
	        // Implement customer notification logic.
	        return "";
	    }

	    private void attendantNotification(boolean weightDiscrepancy) {
	        // Implement attendant notification logic.
	    }
	}


}
