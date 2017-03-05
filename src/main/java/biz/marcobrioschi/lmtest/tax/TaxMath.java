package biz.marcobrioschi.lmtest.tax;

import biz.marcobrioschi.lmtest.util.Money;

public class TaxMath {

	/* 
	 * The value is multiplied to 20 and the ceil(..) round him to the upper integer,
	 * dividing by 20 round him to the upper 0.05;
	 * 
	 * ------------------------------------------------------------------------
	 * 
	 * Note about use case 3 of provided test data:
	 * 
	 * "1 box of imported chocolates at 11.25, with tax 11.85" (tax = 0.6)
	 * It's a imported food, I will apply only "Import duty" of 5%: 11.25 * 5% = 0.5625
	 * that will be 0.6 only if I round to the greater 0.05 (not the nearest).
	 * 
	 * */
	public static Money calculateRoundedTaxValue(Money basePrice, double perc) {
		return new Money(Math.ceil(Math.round(basePrice.getValueAsDouble() * perc * 2000.0)/100.0)/20.0);
	}
	
}
