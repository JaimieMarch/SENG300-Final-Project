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

    @Override
    public void theMassOnTheScaleHasChanged(IElectronicScale scale, Mass mass) {
        // TODO: Add logic to validate the weight change against expected product weights

        context.unBlock();
        System.out.println("Weight updated.");
    }

    @Override
    public void theMassOnTheScaleHasExceededItsLimit(IElectronicScale scale) {
        System.err.println("Weight overload. Assistance required.");
    }

    @Override
    public void theMassOnTheScaleNoLongerExceedsItsLimit(IElectronicScale scale) {
        System.out.println("Weight is back within limits. Resuming normal operations.");
    }
}
