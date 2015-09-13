package biz.marcobrioschi.lmtest;

public class ProductItem {

	private double totalTaxAmount;

	public ProductItem() {
		this.totalTaxAmount = 0.0;
	}

	public String getReceiptDescription() {
		// FIXME Auto-generated method stub
		return null;
	}

	public double getReceiptPrice() {
		// FIXME Auto-generated method stub
		return -1.0;
	}

	public double getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void applyTax(Tax currentTax) {
		totalTaxAmount += currentTax.calculateTaxAmount(this);
	}
	
}
