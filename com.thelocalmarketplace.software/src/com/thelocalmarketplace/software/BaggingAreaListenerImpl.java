package com.thelocalmarketplace.software;

import com.jjjwelectronics.Mass;
import com.jjjwelectronics.scale.ElectronicScaleListener;
import com.jjjwelectronics.scale.IElectronicScale;

//Bagging area listener
public class BaggingAreaListenerImpl extends AbstractIDeviceListener implements ElectronicScaleListener {
    
    // Private constructor to prevent instantiation without context
    private BaggingAreaListenerImpl() {}

    // Public constructor with ApplicationContext
    public BaggingAreaListenerImpl(ApplicationContext context) {
        super(context); // Call to the superclass constructor with context
    }

    // Called when the weight on the scale has changed
    @Override
    public void theMassOnTheScaleHasChanged(IElectronicScale scale, Mass mass) {
        // Unblock any interactions, assuming the change is normal
        context.unBlock();

        // Notify the customer about the weight change
        System.out.println("Notifying customer...");
    }

    // Called when the weight on the scale exceeds the limit
    @Override
    public void theMassOnTheScaleHasExceededItsLimit(IElectronicScale scale) {
        // Print a message to request an overload check
        System.out.println("Please review the overload...");
    }

    // Called when the weight on the scale no longer exceeds the limit
    @Override
    public void theMassOnTheScaleNoLongerExceedsItsLimit(IElectronicScale scale) {
        // This can be implemented to handle the case when the weight is back within the limits
    }
}
