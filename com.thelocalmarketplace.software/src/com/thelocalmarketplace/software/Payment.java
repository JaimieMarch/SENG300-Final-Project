package com.thelocalmarketplace.software;


//        Ananya, Jain: 30196069
//        Emily, Williams: 30122865
//        Jaimie, Marchuk: 30112841
//        Kenny, ZENG: 30151985
//        Yang, YANG: 30156356

import java.math.BigDecimal;

import com.tdc.CashOverloadException;
import com.tdc.DisabledException;
import com.tdc.coin.Coin;
import com.thelocalmarketplace.hardware.BarcodedProduct;
/**
 * Class to make the payment 
 */
public class Payment {


	private static PayWithCoin pc;
	private static Coin coin;
	private static BigDecimal coinValue;
	private static ApplicationContext application;
	private static BarcodedProduct product;

	public Payment(ApplicationContext application){
		this.application = application;
	}

	public void getCoin(Coin cointest) {
	    this.coin = cointest;
	}
	
	/**
	 * Payment is done by receiving coin, compare its value to the total cost needed to pay and display the remaining output. 
	 * Also store the coin in the coin storage
	 * @throws CashOverloadException
	 * @throws DisabledException
	 */
	
	public static void PayCoin()throws CashOverloadException, DisabledException {
		double amount = application.calculateTotalCost(product);
		System.out.println("The Amount to be Paid is: " + amount);
		System.out.println("Insert a Coin");
			while (amount > 0) {
			pc.st.coinValidator.receive(coin);
			coinValue = coin.getValue();
			if (pc.getcost().doubleValue() != amount) {
				pc.validCoinDetected(pc.st.coinValidator, coinValue);
				pc.getcost();
				System.out.println("Coin of Value " + coinValue.toString() + "detected.");
				pc.st.coinStorage.load(coin);
				amount = amount - coinValue.doubleValue();
				System.out.println("The Amount remaining to be paid: " + amount);
				}
			else 
				System.out.println("Amount paid!");		
		}
	
	}
}

