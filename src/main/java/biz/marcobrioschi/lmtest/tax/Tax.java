package biz.marcobrioschi.lmtest.tax;

import biz.marcobrioschi.lmtest.shop.ProductItem;
import biz.marcobrioschi.lmtest.util.Money;

public abstract class Tax {

	public abstract Money calculateTaxAmount(ProductItem currentItem);

}
