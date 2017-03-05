package biz.marcobrioschi.lmtest.tax;

import biz.marcobrioschi.lmtest.shop.ProductItem;
import biz.marcobrioschi.lmtest.util.Money;

public class ImportDutySalesTax extends Tax {

	@Override
	public Money calculateTaxAmount(ProductItem currentItem) {
		
		Money taxValue;
		
		if (currentItem.isImported()) {
			taxValue = TaxMath.calculateRoundedTaxValue(currentItem.getPrice(), TAXPERC);
		} else {
			taxValue = Money.ZERO;
		}
		
		return taxValue;
	
	}

	// In a real case this value can be a configuration
	private static final double TAXPERC = 0.05;
	
}
