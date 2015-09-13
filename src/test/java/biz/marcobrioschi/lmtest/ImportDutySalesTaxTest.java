package biz.marcobrioschi.lmtest;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(mockit.integration.junit4.JMockit.class)
public class ImportDutySalesTaxTest {

	@Test
	public void ImportDutySalesTax_whenTaxLocalItem(
			@Mocked final ProductItem currentItem,
			@Mocked final TaxMath _dontuseme
			) {

		new Expectations() {{
			currentItem.isImported();
			result = false;
		}};

		ImportDutySalesTax importDutySalesTax = new ImportDutySalesTax();
		double taxValue = importDutySalesTax.calculateTaxAmount(currentItem);

		new Verifications() {{
			currentItem.isImported();
			currentItem.getBasePrice(); times = 0;
			TaxMath.calculateRoundedTaxValue(anyDouble, anyDouble); times = 0;
		}};

		assertEquals("No tax for local items", 0.0, taxValue, 0.0);
		
	}

	@Test
	public void ImportDutySalesTax_whenTaxImportedItem(
			@Mocked final ProductItem currentItem,
			@Mocked final TaxMath _dontuseme
			) {
		
		new Expectations() {{
			currentItem.isImported();
			result = true;
			currentItem.getBasePrice();
			result = FIXEDBASEPRICE;
			TaxMath.calculateRoundedTaxValue(anyDouble, anyDouble);
			result = FIXEDTAXAMOUNT;
		}};
		
		ImportDutySalesTax importDutySalesTax = new ImportDutySalesTax();
		double taxValue = importDutySalesTax.calculateTaxAmount(currentItem);

		new Verifications() {{
			currentItem.isImported();
			currentItem.getBasePrice();
			TaxMath.calculateRoundedTaxValue(FIXEDBASEPRICE, anyDouble);
		}};
		
		assertEquals("Tax for imported items", FIXEDTAXAMOUNT, taxValue, 0.0);

	}

	private static final double FIXEDBASEPRICE = 100.0;
	private static final double FIXEDTAXAMOUNT = 1000.0;
	
}
