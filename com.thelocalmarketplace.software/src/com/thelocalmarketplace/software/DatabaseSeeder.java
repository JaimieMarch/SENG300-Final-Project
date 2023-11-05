package com.thelocalmarketplace.software;


//        Ananya, Jain: 30196069
//        Emily, Williams: 30122865
//        Jaimie, Marchuk: 30112841
//        Kenny, ZENG: 30151985
//        Yang, YANG: 30156356

import com.jjjwelectronics.Numeral;
import com.jjjwelectronics.scanner.Barcode;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.PLUCodedProduct;
import com.thelocalmarketplace.hardware.PriceLookUpCode;
import com.thelocalmarketplace.hardware.external.ProductDatabases;

public class DatabaseSeeder {

	public static void seedDatabase() {
            Numeral[] code = {Numeral.zero, Numeral.zero, Numeral.zero, Numeral.one};
            Barcode barcode1 = new Barcode(code);

            Numeral[] code2 = {Numeral.zero, Numeral.zero, Numeral.one, Numeral.one};
            Barcode barcode2 = new Barcode(code2);

        // Create barcoded products
        BarcodedProduct product1 = new BarcodedProduct(barcode1,"Milk", (long) 3.50, 1000);
        BarcodedProduct product2 = new BarcodedProduct(barcode2, "Bread", (long) 2.00, 500);

        // Populate the barcoded product database
        ProductDatabases.BARCODED_PRODUCT_DATABASE.put(product1.getBarcode(), product1);
        ProductDatabases.BARCODED_PRODUCT_DATABASE.put(product2.getBarcode(), product2);

        // Optionally, update the inventory for each product
        ProductDatabases.INVENTORY.put(product1, 10); // suppose there are 10 milks in stock
        ProductDatabases.INVENTORY.put(product2, 20); // suppose there are 20 breads in stock

        // PLU for Dragonfruit
        PriceLookUpCode pluc1 = new PriceLookUpCode("0003");
        PLUCodedProduct product3 = new PLUCodedProduct(pluc1, "Dragonfruit", 197);
        ProductDatabases.PLU_PRODUCT_DATABASE.put(pluc1, product3);
        ProductDatabases.INVENTORY.put(product3, 10);
        
        // PLU for Gatorade Bottle
        PriceLookUpCode pluc2 = new PriceLookUpCode("0004");
        PLUCodedProduct product4 = new PLUCodedProduct(pluc2, "Gatorade Bottle", 999);
        ProductDatabases.PLU_PRODUCT_DATABASE.put(pluc2, product4);
        ProductDatabases.INVENTORY.put(product4, 20);
        
        // PLU for Cereal Box
        PriceLookUpCode pluc3 = new PriceLookUpCode("0005");
        PLUCodedProduct product5 = new PLUCodedProduct(pluc3, "Cereal Box", 579);
        ProductDatabases.PLU_PRODUCT_DATABASE.put(pluc3, product5);
        ProductDatabases.INVENTORY.put(product5, 15);
    }
}
