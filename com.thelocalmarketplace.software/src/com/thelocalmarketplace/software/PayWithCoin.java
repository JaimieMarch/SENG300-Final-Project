package com.thelocalmarketplace.software;


//        Ananya, Jain: 30196069
//        Emily, Williams: 30122865
//        Jaimie, Marchuk: 30112841
//        Kenny, ZENG: 30151985
//        Yang, YANG: 30156356

import java.math.BigDecimal;
	import com.tdc.IComponent;
	import com.tdc.IComponentObserver;
import com.tdc.coin.CoinValidator;
import com.tdc.coin.CoinValidatorObserver;
	import com.thelocalmarketplace.hardware.SelfCheckoutStation;

	import powerutility.PowerGrid;
/**
 * Pay Observer Class 
 */
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
		/**
		*  Listens for the `validCoinDetected` event from a {@code CoinValidator} so
	    * that this object can infer the amount of coins or cash inputed into the
	 	* {@code SelfCheckoutMachine} and therefore its balance.                
		*/
		@Override
		public void validCoinDetected(CoinValidator validator, BigDecimal value) {
			this.totalcost = totalcost.add(value);
			
		}
		@Override
		public void invalidCoinDetected(CoinValidator validator) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Function returns the current value of cash put into the machine that was
		 * observed by this object.
		 * 
		 * @return the balance recorded
		 */

		public BigDecimal getcost() {
			return this.totalcost;
		}
		
 
	}
	  

