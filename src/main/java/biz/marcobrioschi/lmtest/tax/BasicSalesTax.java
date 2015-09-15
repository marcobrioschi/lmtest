package biz.marcobrioschi.lmtest.tax;

import biz.marcobrioschi.lmtest.shop.ProductItem;
import biz.marcobrioschi.lmtest.util.TaxMath;


public class BasicSalesTax extends Tax {

	@Override
	public double calculateTaxAmount(ProductItem currentItem) {

		double taxValue;

		switch(currentItem.getCategory()) {
		
		case book:
		case food:
		case medical:
			taxValue = 0.0;
			break;
			
		case other:
			taxValue = TaxMath.calculateRoundedTaxValue(currentItem.getBasePrice(), TAXPERC);
			break;
			
		default :
			throw new RuntimeException("Missing case management: " + currentItem.getCategory().name());
			
		}

		return taxValue;

	}

	// In a real case this value can be a configuration
	private static final double TAXPERC = 0.10;

}
