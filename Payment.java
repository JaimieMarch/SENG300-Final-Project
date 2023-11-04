package com.thelocalmarketplace.software;

import java.math.BigDecimal;

import com.tdc.CashOverloadException;
import com.tdc.DisabledException;
import com.tdc.coin.Coin;
import com.tdc.coin.CoinValidator;

public class Payment {
	private static PayWithCoin pc;
	private static Coin coin;
	private static BigDecimal coinValue;
	
	
	public static void main()throws CashOverloadException, DisabledException {
		pc.getBalance();
		coinValue = coin.getValue();
		pc.st.coinValidator.receive(coin);
		pc.validCoinDetected(pc.st.coinValidator, coinValue);
		pc.st.coinStorage.load(coin);

	}
		
	}

