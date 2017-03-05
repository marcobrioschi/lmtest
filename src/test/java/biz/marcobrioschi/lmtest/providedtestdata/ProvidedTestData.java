package biz.marcobrioschi.lmtest.providedtestdata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import biz.marcobrioschi.lmtest.shop.ProductItem;
import biz.marcobrioschi.lmtest.shop.ProductItem.ItemCategory;
import biz.marcobrioschi.lmtest.shop.Receipt;
import biz.marcobrioschi.lmtest.shop.Receipt.ReceiptRow;
import biz.marcobrioschi.lmtest.shop.ShoppingBaskets;
import biz.marcobrioschi.lmtest.tax.BasicSalesTax;
import biz.marcobrioschi.lmtest.tax.ImportDutySalesTax;
import biz.marcobrioschi.lmtest.util.Money;

public class ProvidedTestData {

	// Show/Hide provided use cases
	private static final boolean ENABLECONSOLE = false;

	@Test
	public void check_whenDataSet1() {

		ProductItem currentProductItem;

		ShoppingBaskets testShoppingBaskets = new ShoppingBaskets(
				new BasicSalesTax(),
				new ImportDutySalesTax()
				);

		displayStartInput(1);
		
		currentProductItem = new ProductItem("book", new Money(12.49), ItemCategory.book, false);
		testShoppingBaskets.addProductItem(currentProductItem);
		displayProductItem(currentProductItem);

		currentProductItem = new ProductItem("music CD", new Money(14.99), ItemCategory.other, false);
		testShoppingBaskets.addProductItem(currentProductItem);
		displayProductItem(currentProductItem);

		currentProductItem = new ProductItem("chocolate bar", new Money(0.85), ItemCategory.food, false);
		testShoppingBaskets.addProductItem(currentProductItem);
		displayProductItem(currentProductItem);

		displayStartOutput(1);
		
		Receipt testReceipt = testShoppingBaskets.generateReceipt();
		displayReceipt(testReceipt);

		// I created test only for numeric values, not for product description.
		
		assertEquals("book price", new Money(12.49), testReceipt.getRows().get(0).getPrice());
		assertEquals("music CD price", new Money(16.49), testReceipt.getRows().get(1).getPrice());
		assertEquals("chocolate bar price", new Money(0.85), testReceipt.getRows().get(2).getPrice());
		
		assertEquals("Sales Taxes", new Money(1.50), testReceipt.getSalesTaxes());
		assertEquals("Total", new Money(29.83), testReceipt.getTotalPrice());

	}

	@Test
	public void check_whenDataSet2() {

		ProductItem currentProductItem;

		ShoppingBaskets testShoppingBaskets = new ShoppingBaskets(
				new BasicSalesTax(),
				new ImportDutySalesTax()
				);

		displayStartInput(2);
		
		currentProductItem = new ProductItem("box of chocolates", new Money(10.00), ItemCategory.food, true);
		testShoppingBaskets.addProductItem(currentProductItem);
		displayProductItem(currentProductItem);

		currentProductItem = new ProductItem("bottle of perfume", new Money(47.50), ItemCategory.other, true);
		testShoppingBaskets.addProductItem(currentProductItem);
		displayProductItem(currentProductItem);

		displayStartOutput(2);
		
		Receipt testReceipt = testShoppingBaskets.generateReceipt();
		displayReceipt(testReceipt);

		// I created test only for numeric values, not for product description.

		assertEquals("box of chocolates price", new Money(10.50), testReceipt.getRows().get(0).getPrice());
		assertEquals("bottle of perfume price", new Money(54.65), testReceipt.getRows().get(1).getPrice());

		assertEquals("Sales Taxes", new Money(7.65), testReceipt.getSalesTaxes());
		assertEquals("Total", new Money(65.15), testReceipt.getTotalPrice());

	}

	@Test
	public void check_whenDataSet3() {

		ProductItem currentProductItem;

		ShoppingBaskets testShoppingBaskets = new ShoppingBaskets(
				new BasicSalesTax(),
				new ImportDutySalesTax()
				);

		displayStartInput(3);

		currentProductItem = new ProductItem("bottle of perfume", new Money(27.99), ItemCategory.other, true);
		testShoppingBaskets.addProductItem(currentProductItem);
		displayProductItem(currentProductItem);
		
		currentProductItem = new ProductItem("bottle of perfume", new Money(18.99), ItemCategory.other, false);
		testShoppingBaskets.addProductItem(currentProductItem);
		displayProductItem(currentProductItem);
		
		currentProductItem = new ProductItem("packet of headache pills", new Money(9.75), ItemCategory.medical, false);
		testShoppingBaskets.addProductItem(currentProductItem);
		displayProductItem(currentProductItem);

		currentProductItem = new ProductItem("box of chocolates", new Money(11.25), ItemCategory.food, true);
		testShoppingBaskets.addProductItem(currentProductItem);
		displayProductItem(currentProductItem);

		displayStartOutput(3);
		
		Receipt testReceipt = testShoppingBaskets.generateReceipt();
		displayReceipt(testReceipt);

		// I created test only for numeric values, not for product description.

		assertEquals("bottle of perfume[1] price", new Money(32.19), testReceipt.getRows().get(0).getPrice());
		assertEquals("bottle of perfume[2] price", new Money(20.89), testReceipt.getRows().get(1).getPrice());
		assertEquals("packet of headache pills price", new Money(9.75), testReceipt.getRows().get(2).getPrice());
		assertEquals("box of chocolates price", new Money(11.85), testReceipt.getRows().get(3).getPrice());

		assertEquals("Sales Taxes", new Money(6.70), testReceipt.getSalesTaxes());
		assertEquals("Total", new Money(74.68), testReceipt.getTotalPrice());

	}
	
	// Console functions
	
	private void displayStartInput(int n) {
		if (ENABLECONSOLE) {
			System.out.println("--- INPUT [" + n + "]");
		}	
	}
	
	private void displayProductItem(ProductItem currentProductItem) {
		if (ENABLECONSOLE) {
			System.out.println("1 " + (currentProductItem.isImported()?"imported ":"") + currentProductItem.getItemName() + " at " + currentProductItem.getPrice());
		}
	}
	
	private void displayStartOutput(int n) {
		if (ENABLECONSOLE) {
			System.out.println("--- OUTPUT [" + n + "]");
		}	
	}
	
	private void displayReceipt(Receipt testReceipt) {
		if (ENABLECONSOLE) {
			StringBuilder buffer = new StringBuilder();
			for (ReceiptRow row : testReceipt.getRows()) {
				buffer.append(row.getCounter());
				buffer.append(" ");
				buffer.append(row.getDescription());
				buffer.append(": ");
				buffer.append(row.getPrice());
				buffer.append("\n");
			}
			buffer.append("Sales Taxes: ");
			buffer.append(testReceipt.getSalesTaxes());
			buffer.append("\n");
			buffer.append("Total: ");
			buffer.append(testReceipt.getTotalPrice());
			buffer.append("\n");
			System.out.println(buffer);
		}
	}

}
