package com.thelocalmarketplace.software;

import com.jjjwelectronics.Numeral;
import com.jjjwelectronics.scanner.Barcode;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.SelfCheckoutStation;
import com.thelocalmarketplace.hardware.external.ProductDatabases;
import org.junit.Before;
import org.junit.Test;
import powerutility.PowerGrid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class AddItemTest {
    SelfCheckOutStationScanner selfCheckOutStationScanner;

    public class StubApplicationContext extends ApplicationContext {

        private HashMap<Object, DeviceStatus> stubDeviceStatusHashMap = new HashMap<>();
        public Set<BarcodedProduct> stubProductSet = new HashSet<>();
        private BarcodedProduct stubCurrentProduct;
        private boolean isScannerBlocked;
        private boolean isBaggingAreaBlocked;
        private double totalCost;

        public StubApplicationContext(SelfCheckoutStation checkoutStation) {
            super();
            // Not calling super() to avoid actual initialization in the mock
            this.checkoutStation = checkoutStation;
        }


        @Override
        public synchronized void plugIn(PowerGrid grid) {

        }


        @Override
        public synchronized void turnOn() {

        }


        @Override
        public void setCheckoutStationDeviceStatus(DeviceStatus status) {
            stubDeviceStatusHashMap.put(this.checkoutStation, status);
        }

        @Override
        public void setDeviceStatus(Object device, DeviceStatus status) {
            stubDeviceStatusHashMap.put(device, status);
        }

        @Override
        public void store(BarcodedProduct product) {
            if (ProductDatabases.BARCODED_PRODUCT_DATABASE.containsKey(product.getBarcode())) {
                stubCurrentProduct = product;
                stubProductSet.add(product);
            } else {
                System.out.println("Product does not exist in the database. (Mocked)");
            }
        }


        @Override
        public double calculateTotalCost(BarcodedProduct product) {
            // Return mock total cost
            return totalCost;
        }


        @Override
        public boolean verify(BarcodedProduct barcodedProduct) {
            return stubCurrentProduct != null && stubCurrentProduct.equals(barcodedProduct);
        }

        @Override
        public void blockScanner() {
            isScannerBlocked = true;
        }


        @Override
        public void blockBaggingArea() {
            isBaggingAreaBlocked = true;
        }


        @Override
        public void unBlock() {
            isScannerBlocked = false;
            isBaggingAreaBlocked = false;
            stubDeviceStatusHashMap.put(this.checkoutStation.scanner, DeviceStatus.ABLE);
            stubDeviceStatusHashMap.put(this.checkoutStation.baggingArea, DeviceStatus.ABLE);
            stubDeviceStatusHashMap.put(this.checkoutStation, DeviceStatus.ABLE);
        }


        public void setTotalCost(double cost) {
            this.totalCost = cost;
        }

        public boolean isProductStored(BarcodedProduct product) {
            return stubProductSet.contains(product);
        }

        public DeviceStatus getDeviceStatus(Object device) {
            return stubDeviceStatusHashMap.get(device);
        }

        public boolean isScannerBlocked() {
            return isScannerBlocked;
        }

        public boolean isBaggingAreaBlocked() {
            return isBaggingAreaBlocked;
        }

    }


    StubApplicationContext application;
    SelfCheckOutStationScanner scanner;

    SelfCheckoutStation station;

    BarcodedProduct milk;

    BarcodedProduct bread;


    @Before
    public void setup() {

        station = new SelfCheckoutStation();
        scanner = new SelfCheckOutStationScanner() {
            @Override
            public ApplicationContext initApplication() {
                return new StubApplicationContext(station);
            }
        };

        application = (StubApplicationContext) scanner.initApplication();



        Numeral[] code = {Numeral.zero, Numeral.zero, Numeral.zero, Numeral.one};
        Barcode barcode1 = new Barcode(code);

        Numeral[] code2 = {Numeral.zero, Numeral.zero, Numeral.one, Numeral.one};
        Barcode barcode2 = new Barcode(code2);

        // Create barcoded products
        BarcodedProduct milk = new BarcodedProduct(barcode1,"Milk", (long) 3.50, 1000);
        BarcodedProduct bread = new BarcodedProduct(barcode2, "Bread", (long) 2.00, 500);
    }


    @Test
    public void testAddOneProductMethodValid() throws Exception {
        selfCheckOutStationScanner.addProduct(milk);
        assertTrue(application.stubProductSet.contains(milk));

    }

    @Test
    public void testAddMultipleProduct() throws Exception {
        selfCheckOutStationScanner.addProduct(milk);
        selfCheckOutStationScanner.addProduct(bread);

        assertTrue(application.stubProductSet.contains(milk));
        assertTrue(application.stubProductSet.contains(bread));
    }


    @Test(expected = Exception.class)
    public void testAddProduct_ProductDoesNotExistInSystem() {


    }

    @Test
    public void testTotalCostAfterOneAdd(){

    }

    @Test
    public void testTotalCostAfterMultipleAdd(){

    }
}
