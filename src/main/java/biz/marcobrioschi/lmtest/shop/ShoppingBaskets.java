package biz.marcobrioschi.lmtest.shop;

import java.util.ArrayList;
import java.util.List;

import biz.marcobrioschi.lmtest.shop.Receipt.ReceiptRow;
import biz.marcobrioschi.lmtest.tax.Tax;
import biz.marcobrioschi.lmtest.util.Money;

public class ShoppingBaskets {

	/*
	 * Actually unused but I suppose necessary in a real case ...
	 */
	@SuppressWarnings("unused")
	private String id;
	
	private List<ShoppingBasketsItem> items;
	private Tax[] taxes;

	/*
	 * I assumed that taxes are always known before the construction of
	 * the shopping baskets and don't change.
	 */
	public ShoppingBaskets(Tax... taxes) {
		this.items = new ArrayList<ShoppingBasketsItem>();
		this.taxes = taxes;
	}

	public void addProductItem(ProductItem productItem) {
		Money totalTaxAmount = Money.ZERO;
		for (Tax currentTax : taxes) {
			 totalTaxAmount = totalTaxAmount.add(
					 currentTax.calculateTaxAmount(productItem)
					 );
		}
		ShoppingBasketsItem shoppingBasketsItem = new ShoppingBasketsItem();
		shoppingBasketsItem.item = productItem;
		shoppingBasketsItem.taxAmount = totalTaxAmount;
		items.add(shoppingBasketsItem);
	}

	public Receipt generateReceipt() {

		List<ReceiptRow> rows = new ArrayList<ReceiptRow>();
		Money salesTaxes = Money.ZERO;
		Money totalPrice = Money.ZERO;

		/*
		 * To create a receipt that display a unique row for some identical
		 * items, it's necessary to add an aggregation step.
		 */

		for (ShoppingBasketsItem item : items) {
			salesTaxes = salesTaxes.add( item.taxAmount );
			totalPrice = totalPrice.add( item.taxAmount ).add( item.item.getPrice() );
			rows.add(
					new Receipt.ReceiptRow(
							1,
							item.item.getReceiptDescription(),
							item.taxAmount.add( item.item.getPrice() )
							)
					);
		}

		return new Receipt(rows, salesTaxes, totalPrice);

	}

	private class ShoppingBasketsItem {
		ProductItem item;
		Money taxAmount;
	}

}
