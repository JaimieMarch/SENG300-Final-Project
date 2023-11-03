package com.thelocalmarketplace.software;

import com.jjjwelectronics.IDevice;
import com.jjjwelectronics.IDeviceListener;

//Change status of devices
public abstract class AbstractIDeviceListener implements  IDeviceListener {
	public ApplicationContext context;

    public AbstractIDeviceListener(){
        // Default constructor
    }

    public AbstractIDeviceListener(ApplicationContext context){
        // Constructor that initializes the context
        this.context = context;
    }

    // Called when a specific device has been enabled
    @Override
    public void aDeviceHasBeenEnabled(IDevice<? extends IDeviceListener> device) {
        // Notify the software that the current device's status has been set to ENABLED and is ready for work
       this.context.setDeviceStatus(device,DeviceStatus.ABLE);
    }

    // Called when a specific device has been disabled
    @Override
    public void aDeviceHasBeenDisabled(IDevice<? extends IDeviceListener> device) {
        // Notify the software that the current device's status has been set to DISABLED
        this.context.setDeviceStatus(device,DeviceStatus.UNABLE);
    }

    // Called when a specific device has been powered on
    @Override
    public void aDeviceHasBeenTurnedOn(IDevice<? extends IDeviceListener> device) {
        // Change the software's device status from starting to started
        this.context.setDeviceStatus(device,DeviceStatus.STARTED);

        // Enable the device's status
        device.enable();
    }

    // Called when a specific device has been powered off
    @Override
    public void aDeviceHasBeenTurnedOff(IDevice<? extends IDeviceListener> device) {
        // Set the device's status to DISABLED because it's turned off
        device.disable();
    }

}
