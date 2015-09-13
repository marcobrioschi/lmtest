package biz.marcobrioschi.lmtest.tax;

import biz.marcobrioschi.lmtest.shop.ProductItem;
import biz.marcobrioschi.lmtest.util.TaxMath;

public class ImportDutySalesTax extends Tax {

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
