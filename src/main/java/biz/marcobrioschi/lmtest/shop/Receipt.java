/*
 * This is only a DTO for data of receipt.
 */

package biz.marcobrioschi.lmtest.shop;

import java.util.List;

import biz.marcobrioschi.lmtest.util.Money;

public class Receipt {
	
	private List<ReceiptRow> rows;
	private Money salesTaxes;
	private Money totalPrice;
	
	public Receipt(List<ReceiptRow> rows, Money salesTaxes, Money totalPrice) {
		this.rows = rows;
		this.salesTaxes = salesTaxes;
		this.totalPrice = totalPrice;
	}
	
	public List<ReceiptRow> getRows() {
		return rows;
	}

	public Money getSalesTaxes() {
		return salesTaxes;
	}

	public Money getTotalPrice() {
		return totalPrice;
	}

	public static class ReceiptRow {
		
		private int counter;
		private String description;
		private Money price;
		
		public ReceiptRow(int counter, String description, Money price) {
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

		public Money getPrice() {
			return price;
		}

	}
	
}
