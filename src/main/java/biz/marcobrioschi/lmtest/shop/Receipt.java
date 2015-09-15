/*
 * This is only a DTO for data of receipt.
 */

package biz.marcobrioschi.lmtest.shop;

import java.util.List;

public class Receipt {
	
	private List<ReceiptRow> rows;
	private double salesTaxes;
	private double totalPrice;
	
	public Receipt(List<ReceiptRow> rows, double salesTaxes, double totalPrice) {
		this.rows = rows;
		this.salesTaxes = salesTaxes;
		this.totalPrice = totalPrice;
	}
	
	public List<ReceiptRow> getRows() {
		return rows;
	}

	public double getSalesTaxes() {
		return salesTaxes;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public static class ReceiptRow {
		
		private int counter;
		private String description;
		private double price;
		
		public ReceiptRow(int counter, String description, double price) {
			this.counter = counter;
			this.description = description;
			this.price = price;
		}

		public int getCounter() {
			return counter;
		}

		public String getDescription() {
			return description;
		}

		public double getPrice() {
			return price;
		}

	}
	
}
