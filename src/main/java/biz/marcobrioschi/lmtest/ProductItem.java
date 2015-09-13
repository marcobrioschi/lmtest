package biz.marcobrioschi.lmtest;

public class ProductItem {

	private double basePrice;
	private ItemCategory category;
	private boolean imported;
	private double totalTaxAmount;

	public ProductItem(double basePrice, ItemCategory category, boolean imported) {
		this.basePrice = basePrice;
		this.category = category;
		this.imported = imported;
		this.totalTaxAmount = 0.0;
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
		// FIXME Auto-generated method stub
		return null;
	}

	public double getReceiptPrice() {
		return basePrice + totalTaxAmount;
	}

	public void applyTax(Tax currentTax) {
		totalTaxAmount += currentTax.calculateTaxAmount(this);
	}

	public enum ItemCategory {
		book,
		food,
		medical,
		other;
	}

}
