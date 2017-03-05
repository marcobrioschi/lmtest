package biz.marcobrioschi.lmtest.tax;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Test;
import org.junit.runner.RunWith;

import biz.marcobrioschi.lmtest.shop.ProductItem;
import biz.marcobrioschi.lmtest.util.Money;

@RunWith(mockit.integration.junit4.JMockit.class)
public class ImportDutySalesTaxTest {

	@Test
	public void calculateTaxAmount_whenTaxLocalItem(
			@Mocked final ProductItem currentItem,
			@Mocked final TaxMath _dontuseme_
			) {

		new Expectations() {{
			currentItem.isImported();
			result = false;
		}};

		ImportDutySalesTax importDutySalesTax = new ImportDutySalesTax();
		Money taxValue = importDutySalesTax.calculateTaxAmount(currentItem);

		new Verifications() {{
			currentItem.isImported();
			currentItem.getPrice(); times = 0;
			TaxMath.calculateRoundedTaxValue((Money) any, anyDouble); times = 0;
		}};

		assertEquals("No tax for local items", Money.ZERO, taxValue);
		
	}

	@Test
	public void calculateTaxAmount_whenTaxImportedItem(
			@Mocked final ProductItem currentItem,
			@Mocked final TaxMath _dontuseme_
			) {
		
		new Expectations() {{
			currentItem.isImported();
			result = true;
			currentItem.getPrice();
			result = FIXEDBASEPRICE;
			TaxMath.calculateRoundedTaxValue((Money) any, anyDouble);
			result = FIXEDTAXAMOUNT;
		}};
		
		ImportDutySalesTax importDutySalesTax = new ImportDutySalesTax();
		Money taxValue = importDutySalesTax.calculateTaxAmount(currentItem);

		new Verifications() {{
			currentItem.isImported();
			currentItem.getPrice();
			TaxMath.calculateRoundedTaxValue((Money) any, anyDouble);
		}};
		
		assertEquals("Tax for imported items", FIXEDTAXAMOUNT, taxValue);
		
	}

	private static final Money FIXEDBASEPRICE = new Money(100.0);
	private static final Money FIXEDTAXAMOUNT = new Money(2341.5);
	
}
