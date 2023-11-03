package com.thelocalmarketplace.software;

import java.util.Map;

import com.jjjwelectronics.scanner.Barcode;
import com.jjjwelectronics.scanner.BarcodeScannerListener;
import com.jjjwelectronics.scanner.IBarcodeScanner;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.external.ProductDatabases;

//Barcode scanner listener
public class BarcodeScannerListenerImpl extends AbstractIDeviceListener implements BarcodeScannerListener {

    // Private constructor to prevent instantiation without context
    private BarcodeScannerListenerImpl(){
        super(); // Call to superclass default constructor
    }

    // Public constructor with ApplicationContext
    public BarcodeScannerListenerImpl(ApplicationContext context) {
        super(context); // Call to superclass constructor with context
    }

    @Override
    public void aBarcodeHasBeenScanned(IBarcodeScanner barcodeScanner, Barcode barcode) {
    	
    	Map<Barcode, BarcodedProduct> barcodedProductDatabase = ProductDatabases.BARCODED_PRODUCT_DATABASE;

        // Retrieve the product details from the database
        BarcodedProduct barcodedProduct = barcodedProductDatabase.get(barcode);

        if (barcodedProduct != null) {
            // Validate the product information
            boolean res = context.verify(barcodedProduct);

            if (!res) {
                System.out.println("The product does not match...");
            }
        } else {
            System.out.println("Scanned barcode does not exist in the database.");
        }
    }
}
