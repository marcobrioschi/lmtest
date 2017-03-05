package biz.marcobrioschi.lmtest.shop;

import biz.marcobrioschi.lmtest.util.Money;

public class ProductItem {

	/*
	 * Actually unused but I suppose necessary in a real case ...
	 */
	@SuppressWarnings("unused")
	private String id;
	
	private String itemName;
	private Money price;
	private ItemCategory category;
	private boolean imported;

	public ProductItem(String itemName, Money price, ItemCategory category, boolean imported) {
		this.itemName = itemName;
		this.price = price;
		this.category = category;
		this.imported = imported;
	}

	public String getItemName() {
		return itemName;
	}

	public Money getPrice() {
		return price;
	}
	
	public ItemCategory getCategory() {
		return category;
	}

	public boolean isImported() {
		return imported;
	}

	public String getReceiptDescription() {
		String receiptDescription;
		if (this.isImported()) {
			receiptDescription = "imported " + itemName;
		} else {
			receiptDescription = itemName;
		}
		return receiptDescription;
	}

	public enum ItemCategory {
		book,
		food,
		medical,
		other;
	}

}
