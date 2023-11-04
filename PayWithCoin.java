package com.thelocalmarketplace.software;

import java.math.BigDecimal;
	import com.tdc.IComponent;
	import com.tdc.IComponentObserver;
	import com.tdc.coin.AbstractCoinValidator;
	import com.tdc.coin.CoinValidatorObserver;
	import com.thelocalmarketplace.hardware.SelfCheckoutStation;

	import powerutility.PowerGrid;
/**
* Observer Class to keep track of the events 
*/

public class PayWithCoin implements CoinValidatorObserver{
	private PowerGrid powergrid;
	public SelfCheckoutStation st;
	private BigDecimal totalcost;
	/**
	* Constructor to turn on the self checkout system and set initial balance to zero.
	*/ 
	public PayWithCoin() {
		st.turnOn();
		st.plugIn(powergrid);
		totalbalance = BigDecimal.ZERO;
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
		public void validCoinDetected(AbstractCoinValidator validator, BigDecimal value) {
			this.totalcost = totalcost.add(value);
			
		}
		@Override
		public void invalidCoinDetected(AbstractCoinValidator validator) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Function returns the current value of cash put into the machine that was
		 * observed by this object.
		 * 
		 * @return the balance recorded
		 */
		public BigDecimal getcost() {
			return this.totalbalance;
		}
		
 
	}
	  

