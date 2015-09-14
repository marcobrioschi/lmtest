package biz.marcobrioschi.lmtest.shop;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Test;
import org.junit.runner.RunWith;

import biz.marcobrioschi.lmtest.shop.ProductItem.ItemCategory;
import biz.marcobrioschi.lmtest.tax.Tax;

@RunWith(mockit.integration.junit4.JMockit.class)
public class ProductItemTest {
	
	@Test
	public void ProductItem_whenApplyOneTax(
			@Mocked final Tax firstTax
			) {
		
		new Expectations() {{
			firstTax.calculateTaxAmount((ProductItem)any);
			result = FIXEDFIRSTTAXAMOUNT; 
		}};
		
		final ProductItem testItem = new ProductItem("magic item", FIXEDBASEPRICE, ItemCategory.book, true);
		
		assertEquals("Item start with no tax", 0.0, testItem.getTotalTaxAmount(), 0.0);

		testItem.applyTax(firstTax);
		
		new Verifications() {{
			firstTax.calculateTaxAmount(testItem);
		}};
		
		assertEquals("Item registered amount of tax", FIXEDFIRSTTAXAMOUNT, testItem.getTotalTaxAmount(), 0.0);
		assertEquals("Item compose receipt value", FIXEDBASEPRICE + FIXEDFIRSTTAXAMOUNT, testItem.getReceiptPrice(), 0.0);
		
	}

	@Test
	public void ProductItem_whenApplyTwoTaxes(
			@Mocked final Tax firstTax,
			@Mocked final Tax secondTax
			) {
		
		new Expectations() {{
			firstTax.calculateTaxAmount((ProductItem)any);
			result = FIXEDFIRSTTAXAMOUNT;
			secondTax.calculateTaxAmount((ProductItem)any);
			result = FIXEDSECONDTAXAMOUNT;
		}};
		
		final ProductItem testItem = new ProductItem("special item", FIXEDBASEPRICE, ItemCategory.other, false);
		
		assertEquals("Item start with no tax", 0.0, testItem.getTotalTaxAmount(), 0.0);

		testItem.applyTax(firstTax);
		testItem.applyTax(secondTax);
		
		new Verifications() {{
			firstTax.calculateTaxAmount(testItem);
			secondTax.calculateTaxAmount(testItem);
		}};
		
		assertEquals("Item registered amount of two taxes", FIXEDFIRSTTAXAMOUNT + FIXEDSECONDTAXAMOUNT, testItem.getTotalTaxAmount(), 0.0);
		assertEquals("Item compose receipt value", FIXEDBASEPRICE + FIXEDFIRSTTAXAMOUNT + FIXEDSECONDTAXAMOUNT, testItem.getReceiptPrice(), 0.0);
		
	}

	private static final double FIXEDBASEPRICE = 12.55;
	private static final double FIXEDFIRSTTAXAMOUNT = 34.12;
	private static final double FIXEDSECONDTAXAMOUNT = 78.56;

}