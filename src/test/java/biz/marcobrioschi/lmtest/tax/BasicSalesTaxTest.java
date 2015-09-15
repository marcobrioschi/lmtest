package biz.marcobrioschi.lmtest.tax;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Test;
import org.junit.runner.RunWith;

import biz.marcobrioschi.lmtest.shop.ProductItem;
import biz.marcobrioschi.lmtest.shop.ProductItem.ItemCategory;
import biz.marcobrioschi.lmtest.tax.BasicSalesTax;
import biz.marcobrioschi.lmtest.util.TaxMath;

@RunWith(mockit.integration.junit4.JMockit.class)
public class BasicSalesTaxTest {
	
	@Test
	public void calculateTaxAmount_whenTaxNormalItems(
			@Mocked final ProductItem currentItem,
			@Mocked final TaxMath _dontuseme
			) {
		
		new Expectations() {{
			currentItem.getCategory();
			result = ItemCategory.other;
			currentItem.getBasePrice();
			result = FIXEDBASEPRICE;
			TaxMath.calculateRoundedTaxValue(anyDouble, anyDouble);
			result = FIXEDTAXAMOUNT;
		}};
		
		BasicSalesTax basicSalesTax = new BasicSalesTax();
		double taxValue = basicSalesTax.calculateTaxAmount(currentItem);
		
		new Verifications() {{
			currentItem.getCategory();
			currentItem.getBasePrice();
			TaxMath.calculateRoundedTaxValue(FIXEDBASEPRICE, anyDouble);
		}};
		
		assertEquals("Tax for normal items", FIXEDTAXAMOUNT, taxValue, 0.0);

	}
	
	@Test
	public void calculateTaxAmount_whenTaxExemptItems(
			@Mocked final ProductItem currentItem,
			@Mocked final TaxMath _dontuseme
			) {
		
		new Expectations() {{
			currentItem.getCategory();
			result = ItemCategory.medical;
		}};

		BasicSalesTax basicSalesTax = new BasicSalesTax();
		double taxValue = basicSalesTax.calculateTaxAmount(currentItem);

		new Verifications() {{
			currentItem.getCategory();
			currentItem.getBasePrice(); times = 0;
			TaxMath.calculateRoundedTaxValue(anyDouble, anyDouble); times = 0;
		}};
		
		assertEquals("No tax for exempt items", 0.0, taxValue, 0.0);

	}
	
	/*
	 * When someone add a value to the enumeration ItemCategory this test fails so
	 * the programmer can't forget to manage explicitly the value.
	 */
	@Test
	public void check_whenPeopleAddEnumValues() {
		
		BasicSalesTax basicSalesTax = new BasicSalesTax();
		for (ItemCategory c : ItemCategory.values()) {
			ProductItem item = new ProductItem("magic item", 1.0, c, false);
			basicSalesTax.calculateTaxAmount(item);
		}
		
	}

	private static final double FIXEDBASEPRICE = 321.9;
	private static final double FIXEDTAXAMOUNT = 8765.4;
	
}
