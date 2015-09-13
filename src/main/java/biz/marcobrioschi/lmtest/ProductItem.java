package biz.marcobrioschi.lmtest;

public class ProductItem {

	private double basePrice;
	private boolean imported;
	private double totalTaxAmount;

	public ProductItem(double basePrice, boolean imported) {
		this.basePrice = basePrice;
		this.imported = imported;
		this.totalTaxAmount = 0.0;
	}

	public double getBasePrice() {
		return basePrice;
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
	
}
