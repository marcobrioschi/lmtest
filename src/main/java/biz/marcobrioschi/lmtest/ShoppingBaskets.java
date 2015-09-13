package biz.marcobrioschi.lmtest;

import java.util.ArrayList;
import java.util.List;

import biz.marcobrioschi.lmtest.Receipt.ReceiptRow;

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
			salesTaxes += item.getTotalTaxAmount();
			totalPrice += item.getReceiptPrice();
			rows.add(
					new Receipt.ReceiptRow(1, item.getReceiptDescription(), item.getReceiptPrice())
					);
		}
		
		return new Receipt(rows, salesTaxes, totalPrice);
		
	}

}
