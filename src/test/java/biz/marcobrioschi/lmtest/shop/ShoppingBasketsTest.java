package biz.marcobrioschi.lmtest.shop;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Test;
import org.junit.runner.RunWith;

import biz.marcobrioschi.lmtest.tax.Tax;
import biz.marcobrioschi.lmtest.util.Money;

@RunWith(mockit.integration.junit4.JMockit.class)
public class ShoppingBasketsTest {

	@Test
	public void generateReceipt_case_A(
			@Mocked final ProductItem itemA,
			@Mocked final ProductItem itemB,
			@Mocked final Tax firstTax,
			@Mocked final Tax secondTax,
			@Mocked final Tax thirdTax
			) {
	
		new Expectations() {{
			itemA.getPrice();
			result = new Money(1.0);
			itemB.getPrice();
			result = new Money(2.0);
			firstTax.calculateTaxAmount((ProductItem)any);
			result = new Money(100.0);
			secondTax.calculateTaxAmount((ProductItem)any);
			result = new Money(200.0);
			thirdTax.calculateTaxAmount(itemA);
			result = new Money(400.0);
			thirdTax.calculateTaxAmount(itemB);
			result = new Money(800.0);
		}};
		
		ShoppingBaskets currentShoppingBaskets =
				new ShoppingBaskets(firstTax, secondTax, thirdTax);

		currentShoppingBaskets.addProductItem(itemA);
		currentShoppingBaskets.addProductItem(itemB);
		
		Receipt receipt = currentShoppingBaskets.generateReceipt();
		
		new Verifications() {{
			thirdTax.calculateTaxAmount(itemA);
			secondTax.calculateTaxAmount(itemA);
			firstTax.calculateTaxAmount(itemA);
			firstTax.calculateTaxAmount(itemB);
			secondTax.calculateTaxAmount(itemB);
			thirdTax.calculateTaxAmount(itemB);
		}};
		
		assertEquals("Check ShoppingBaskets internal list", 2, receipt.getRows().size());
		assertEquals("Check tax", new Money(1800.0), receipt.getSalesTaxes());
		assertEquals("Check tax", new Money(1803.0), receipt.getTotalPrice());
		
	}
	
	@Test
	public void generateReceipt_case_B(
			@Mocked final ProductItem itemA,
			@Mocked final ProductItem itemB,
			@Mocked final Tax theTax
			) {
		
		new Expectations() {{
			itemA.getReceiptDescription();
			result = "AAA aaa";
			itemA.getPrice();
			result = new Money(12.34);
			itemB.getReceiptDescription();
			result = "bBbBbB";
			itemB.getPrice();
			result = new Money(43.21);
			theTax.calculateTaxAmount((ProductItem)any);
			result = new Money(123.11);
		}};
		
		ShoppingBaskets currentShoppingBaskets =
				new ShoppingBaskets(theTax);

		currentShoppingBaskets.addProductItem(itemA);
		currentShoppingBaskets.addProductItem(itemB);
		
		Receipt testReceipt = currentShoppingBaskets.generateReceipt();
		
		new Verifications() {{
			itemA.getReceiptDescription();
			itemA.getPrice();
			itemB.getReceiptDescription();
			itemB.getPrice();
			theTax.calculateTaxAmount(itemA);
			theTax.calculateTaxAmount(itemB);
		}};
		
		assertEquals("Two items, two rows", 2, testReceipt.getRows().size());
		assertEquals("Only one item of this type", 1, testReceipt.getRows().get(0).getCounter());
		assertEquals("Check generated description", "AAA aaa", testReceipt.getRows().get(0).getDescription());
		assertEquals("Check generated total price with taxes", new Money(135.45), testReceipt.getRows().get(0).getPrice());
		assertEquals("Only one item of this type", 1, testReceipt.getRows().get(1).getCounter());
		assertEquals("Check generated description", "bBbBbB", testReceipt.getRows().get(1).getDescription());
		assertEquals("Check generated total price with tax", new Money(166.32), testReceipt.getRows().get(1).getPrice());
		assertEquals("Check total taxes", new Money(246.22), testReceipt.getSalesTaxes());
		assertEquals("Check total price with taxes", new Money(301.77), testReceipt.getTotalPrice());

	}
	
}
