package biz.marcobrioschi.lmtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TaxMathTest {

	@Test
	public void TaxMath_whenCheckRoundedUp() {
		assertEquals(0.05, TaxMath.calculateRoundedTaxValue(0.7, 0.1), 0.0);
		assertEquals(0.10, TaxMath.calculateRoundedTaxValue(0.8, 0.1), 0.0);
		assertEquals(0.10, TaxMath.calculateRoundedTaxValue(0.9, 0.1), 0.0);
		assertEquals(0.10, TaxMath.calculateRoundedTaxValue(1.0, 0.1), 0.0);
		assertEquals(0.10, TaxMath.calculateRoundedTaxValue(1.1, 0.1), 0.0);
		assertEquals(0.10, TaxMath.calculateRoundedTaxValue(1.2, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.3, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.4, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.5, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.6, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.7, 0.1), 0.0);
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(1.8, 0.1), 0.0);
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(1.9, 0.1), 0.0);
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(2.0, 0.1), 0.0);
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(2.1, 0.1), 0.0);
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(2.2, 0.1), 0.0);
		assertEquals(0.25, TaxMath.calculateRoundedTaxValue(2.3, 0.1), 0.0);
	}
	
}
