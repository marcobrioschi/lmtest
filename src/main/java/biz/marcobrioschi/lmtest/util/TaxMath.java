package biz.marcobrioschi.lmtest.util;

public class TaxMath {

	/* 
	 * From use case 3:
	 * "1 box of imported chocolates at 11.25, with tax 11.85" (tax = 0.6)
	 * It's a imported food, I will apply only "Import duty" of 5%: 11.25 * 5% = 0.5625
	 * that will be 0.6 only if I round to the greater 0.05 (not the nearest).
	 * 
	 * */
	public static double calculateRoundedTaxValue(double basePrice, double perc) {
		return Math.ceil(Math.round(basePrice * perc * 2000.0)/100.0)/20.0;
	}
	
	public static double roundToTaxPrecision(double value) {
		return Math.round(value * 100.0)/100.0;
	}

}
