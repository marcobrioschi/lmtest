package biz.marcobrioschi.lmtest;

public class ImportDutySalesTax implements Tax {

	@Override
	public double calculateTaxAmount(ProductItem currentItem) {
		
		double taxValue;
		
		if (currentItem.isImported()) {
			taxValue = TaxMath.calculateRoundedTaxValue(currentItem.getBasePrice(), TAXPERC);
		} else {
			taxValue = 0.0;
		}
		
		return taxValue;
	
	}

	private static final double TAXPERC = 0.05;
	
}
