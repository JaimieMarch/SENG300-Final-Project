package com.thelocalmarketplace.software;


//        Ananya, Jain: 30196069
//        Emily, Williams: 30122865
//        Jaimie, Marchuk: 30112841
//        Kenny, ZENG: 30151985
//        Yang, YANG: 30156356

import com.jjjwelectronics.IDevice;
import com.jjjwelectronics.IDeviceListener;

//Change status of devices
public abstract class AbstractIDeviceListener implements  IDeviceListener {
	public ApplicationContext context;

    public AbstractIDeviceListener(){
        // Default constructor
    }

    public AbstractIDeviceListener(ApplicationContext context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        this.context = context;
    }

    // Called when a specific device has been enabled
    @Override
    public void aDeviceHasBeenEnabled(IDevice<? extends IDeviceListener> device) {
    	if (device == null) {
            throw new IllegalArgumentException("Device cannot be null");
        }
        this.context.setDeviceStatus(device, DeviceStatus.ABLE);
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
        this.context.setDeviceStatus(device,DeviceStatus.START);

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
