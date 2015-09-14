package biz.marcobrioschi.lmtest.shop;

import java.util.ArrayList;
import java.util.List;

import biz.marcobrioschi.lmtest.shop.Receipt.ReceiptRow;
import biz.marcobrioschi.lmtest.tax.Tax;
import biz.marcobrioschi.lmtest.util.TaxMath;

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

	public Receipt generateReceipt() {
		
		List<ReceiptRow> rows = new ArrayList<ReceiptRow>();
		double salesTaxes = 0.0;
		double totalPrice = 0.0;
		
		for (ProductItem item : items) {
			salesTaxes = TaxMath.roundToTaxPrecision(salesTaxes + item.getTotalTaxAmount());
			totalPrice = TaxMath.roundToTaxPrecision(totalPrice + item.getReceiptPrice());
			rows.add(
					new Receipt.ReceiptRow(1, item.getReceiptDescription(), item.getReceiptPrice())
					);
		}
		
		return new Receipt(rows, salesTaxes, totalPrice);
		
	}

}
