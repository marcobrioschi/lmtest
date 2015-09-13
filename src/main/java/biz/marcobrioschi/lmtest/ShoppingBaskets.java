package biz.marcobrioschi.lmtest;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBaskets {

	List<ProductItem> items;
	Tax[] taxes;
	
	public ShoppingBaskets(Tax... taxes) {
		this.items = new ArrayList<ProductItem>();
		this.taxes = taxes;
	}

	public void addProductItem(ProductItem item) {
		for (Tax currentTax : taxes) {
			item.applyTax(currentTax);
		}
		items.add(item);
	}

}
