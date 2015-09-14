package biz.marcobrioschi.lmtest.shop;

import biz.marcobrioschi.lmtest.tax.Tax;
import biz.marcobrioschi.lmtest.util.TaxMath;

public class ProductItem {

	private String itemName;
	private double basePrice;
	private ItemCategory category;
	private boolean imported;
	private double totalTaxAmount;

	public ProductItem(String itemName, double basePrice, ItemCategory category, boolean imported) {
		this.itemName = itemName;
		this.basePrice = basePrice;
		this.category = category;
		this.imported = imported;
		this.totalTaxAmount = 0.0;
	}

	public String getItemName() {
		return itemName;
	}

	public double getBasePrice() {
		return basePrice;
	}
	
	public ItemCategory getCategory() {
		return category;
	}

	public boolean isImported() {
		return imported;
	}

	public double getTotalTaxAmount() {
		return totalTaxAmount;
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

	public double getReceiptPrice() {
		return TaxMath.roundToTaxPrecision(basePrice + totalTaxAmount);
	}

	public void applyTax(Tax currentTax) {
		totalTaxAmount = TaxMath.roundToTaxPrecision(totalTaxAmount + currentTax.calculateTaxAmount(this));
	}

	public enum ItemCategory {
		book,
		food,
		medical,
		other;
	}

}
