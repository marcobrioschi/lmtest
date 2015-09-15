package biz.marcobrioschi.lmtest.shop;

import java.util.ArrayList;
import java.util.List;

import biz.marcobrioschi.lmtest.shop.Receipt.ReceiptRow;
import biz.marcobrioschi.lmtest.tax.Tax;
import biz.marcobrioschi.lmtest.util.TaxMath;

public class ShoppingBaskets {

	private List<ProductItem> items;
	private Tax[] taxes;
	
	/*
	 * I assumed that taxes are always known before the construction of
	 * the shopping baskets and don't change.
	 */
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
		
		/*
		 * To create a receipt that display a unique row for some identical
		 * items, it's necessary to add an aggregation step.
		 */
		
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
