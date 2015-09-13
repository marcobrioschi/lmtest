package biz.marcobrioschi.lmtest;

public class TaxMath {

	// FIXME: Check for use Math.floor(...)
	public static double calculateRoundedTaxValue(double basePrice, double perc) {
		return Math.round(basePrice * perc * 20.0)/20.0;
	}
	
}
