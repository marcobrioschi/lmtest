package biz.marcobrioschi.lmtest.tax;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Test;
import org.junit.runner.RunWith;

import biz.marcobrioschi.lmtest.shop.ProductItem;
import biz.marcobrioschi.lmtest.shop.ProductItem.ItemCategory;
import biz.marcobrioschi.lmtest.util.Money;

@RunWith(mockit.integration.junit4.JMockit.class)
public class BasicSalesTaxTest {
	
	@Test
	public void calculateTaxAmount_whenTaxNormalItems(
			@Mocked final ProductItem currentItem,
			@Mocked final TaxMath _dontuseme_
			) {
		
		new Expectations() {{
			currentItem.getCategory();
			result = ItemCategory.other;
			currentItem.getPrice();
			result = FIXEDBASEPRICE;
			TaxMath.calculateRoundedTaxValue((Money) any, anyDouble);
			result = FIXEDTAXAMOUNT;
		}};
		
		BasicSalesTax basicSalesTax = new BasicSalesTax();
		Money taxValue = basicSalesTax.calculateTaxAmount(currentItem);
		
		new Verifications() {{
			currentItem.getCategory();
			currentItem.getPrice();
			TaxMath.calculateRoundedTaxValue((Money) any, anyDouble);
		}};
		
		assertEquals("Tax for normal items", FIXEDTAXAMOUNT, taxValue);

	}
	
	@Test
	public void calculateTaxAmount_whenTaxExemptItems(
			@Mocked final ProductItem currentItem,
			@Mocked final TaxMath _dontuseme_
			) {
		
		new Expectations() {{
			currentItem.getCategory();
			result = ItemCategory.medical;
		}};

		BasicSalesTax basicSalesTax = new BasicSalesTax();
		Money taxValue = basicSalesTax.calculateTaxAmount(currentItem);

		new Verifications() {{
			currentItem.getCategory();
			currentItem.getPrice(); times = 0;
			TaxMath.calculateRoundedTaxValue((Money) any, anyDouble); times = 0;
		}};
		
		assertEquals("No tax for exempt items", Money.ZERO, taxValue);

	}
	
	/*
	 * When someone add a value to the enumeration ItemCategory this test fails so
	 * the programmer can't forget to manage explicitly the value.
	 */
	@Test
	public void check_whenPeopleAddEnumValues() {
		
		BasicSalesTax basicSalesTax = new BasicSalesTax();
		for (ItemCategory c : ItemCategory.values()) {
			ProductItem item = new ProductItem("magic item", Money.ZERO, c, false);
			basicSalesTax.calculateTaxAmount(item);
		}
		
	}

	private static final Money FIXEDBASEPRICE = new Money(321.9);
	private static final Money FIXEDTAXAMOUNT = new Money(1000.0);
	
}
