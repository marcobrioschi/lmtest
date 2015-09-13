package biz.marcobrioschi.lmtest.tax;

import biz.marcobrioschi.lmtest.shop.ProductItem;

public abstract class Tax {

	public abstract double calculateTaxAmount(ProductItem currentItem);

}
