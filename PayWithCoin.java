package com.thelocalmarketplace.software;

import java.math.BigDecimal;
	import com.tdc.IComponent;
	import com.tdc.IComponentObserver;
	import com.tdc.coin.AbstractCoinValidator;
	import com.tdc.coin.CoinValidatorObserver;
	import com.thelocalmarketplace.hardware.SelfCheckoutStation;

	import powerutility.PowerGrid;

public class PayWithCoin implements CoinValidatorObserver{
	private PowerGrid powergrid;
	public SelfCheckoutStation st;
	private BigDecimal totalcost;
	public PayWithCoin() {
		st.turnOn();
		st.plugIn(powergrid);
		totalcost = BigDecimal.ZERO;
	}
			
			
	
		@Override
		public void enabled(IComponent<? extends IComponentObserver> component) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void disabled(IComponent<? extends IComponentObserver> component) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void turnedOn(IComponent<? extends IComponentObserver> component) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void turnedOff(IComponent<? extends IComponentObserver> component) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void validCoinDetected(AbstractCoinValidator validator, BigDecimal value) {
			this.totalcost = totalcost.add(value);
			
		}
		@Override
		public void invalidCoinDetected(AbstractCoinValidator validator) {
			// TODO Auto-generated method stub
			
		}
		public BigDecimal getcost() {
			return this.totalcost;
		}
		
 
	}
	  

