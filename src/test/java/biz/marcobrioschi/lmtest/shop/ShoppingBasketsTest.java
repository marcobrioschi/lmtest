package biz.marcobrioschi.lmtest.shop;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Test;
import org.junit.runner.RunWith;

import biz.marcobrioschi.lmtest.tax.Tax;

@RunWith(mockit.integration.junit4.JMockit.class)
public class ShoppingBasketsTest {

	@Test
	public void ShoppingBaskets_whenAddItemAddAndApplyTax(
			@Mocked final ProductItem itemA,
			@Mocked final ProductItem itemB,
			@Mocked final Tax firstTax,
			@Mocked final Tax secondTax,
			@Mocked final Tax thirdTax
			) {
		
		ShoppingBaskets currentShoppingBaskets =
				new ShoppingBaskets(firstTax, secondTax, thirdTax);

		currentShoppingBaskets.addProductItem(itemA);
		currentShoppingBaskets.addProductItem(itemB);
		
		new Verifications() {{
			itemA.applyTax(thirdTax);
			itemA.applyTax(secondTax);
			itemA.applyTax(firstTax);
			itemB.applyTax(firstTax);
			itemB.applyTax(secondTax);
			itemB.applyTax(thirdTax);
		}};
		
		assertEquals("Check ShoppingBaskets internal list", 2, currentShoppingBaskets.items.size());
		
	}
	
	@Test
	public void ShoppingBaskets_whenCreateReceipt(
			@Mocked final ProductItem itemA,
			@Mocked final ProductItem itemB,
			@Mocked final Tax firstTax
			) {
		
		new Expectations() {{
			itemA.getReceiptDescription();
			result = RECEIPTDESC_A;
			itemA.getTotalTaxAmount();
			result = RECEIPTTAX_A;
			itemA.getReceiptPrice();
			result = RECEIPTPRICE_A;
			itemB.getReceiptDescription();
			result = RECEIPTDESC_B;
			itemB.getTotalTaxAmount();
			result = RECEIPTTAX_B;
			itemB.getReceiptPrice();
			result = RECEIPTPRICE_B;
		}};
		
		ShoppingBaskets currentShoppingBaskets =
				new ShoppingBaskets(firstTax);

		currentShoppingBaskets.addProductItem(itemA);
		currentShoppingBaskets.addProductItem(itemB);
		
		Receipt testReceipt = currentShoppingBaskets.generateReceipt();
		
		new Verifications() {{
			itemA.getReceiptDescription();
			itemA.getTotalTaxAmount();
			itemA.getReceiptPrice();
			itemB.getReceiptDescription();
			itemB.getTotalTaxAmount();
			itemB.getReceiptPrice();
		}};
		
		assertEquals("Two items, two rows", 2, testReceipt.getRows().size());
		assertEquals("Only one item of this type", 1, testReceipt.getRows().get(0).getCounter());
		assertEquals("Check generated description", RECEIPTDESC_A, testReceipt.getRows().get(0).getDescription());
		assertEquals("Check generated total price with taxes", RECEIPTPRICE_A, testReceipt.getRows().get(0).getPrice(), 0.0);
		assertEquals("Only one item of this type", 1, testReceipt.getRows().get(1).getCounter());
		assertEquals("Check generated description", RECEIPTDESC_B, testReceipt.getRows().get(1).getDescription());
		assertEquals("Check generated total price with tax", RECEIPTPRICE_B, testReceipt.getRows().get(1).getPrice(), 0.0);
		assertEquals("Check total taxes", RECEIPTTAX_A + RECEIPTTAX_B, testReceipt.getSalesTaxes(), 0.0);
		assertEquals("Check total price with taxes", RECEIPTPRICE_A + RECEIPTPRICE_B, testReceipt.getTotalPrice(), 0.0);

	}
	
	private static final String RECEIPTDESC_A = "Item AaA";
	private static final double RECEIPTPRICE_A = 11.22;
	private static final double RECEIPTTAX_A = 55.44;
	private static final String RECEIPTDESC_B = "BBB Item";
	private static final double RECEIPTPRICE_B = 333;	
	private static final double RECEIPTTAX_B = 0.77;
	
}
