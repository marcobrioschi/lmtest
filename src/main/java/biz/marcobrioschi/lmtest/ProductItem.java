package biz.marcobrioschi.lmtest;

public class ProductItem {

	private double totalTaxAmount;

	public ProductItem() {
		this.totalTaxAmount = 0.0;
	}
	
	public double getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void applyTax(Tax currentTax) {
		totalTaxAmount += currentTax.calculateTaxAmount(this);
	}
	
}
