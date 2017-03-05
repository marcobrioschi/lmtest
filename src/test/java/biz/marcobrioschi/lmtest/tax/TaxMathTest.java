package biz.marcobrioschi.lmtest.tax;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import biz.marcobrioschi.lmtest.tax.TaxMath;
import biz.marcobrioschi.lmtest.util.Money;

public class TaxMathTest {

	@Test
	public void check_calculateRoundedTaxValue() {
		
		assertEquals(new Money(0.10), TaxMath.calculateRoundedTaxValue(new Money(0.7), 0.1));
		assertEquals(new Money(0.10), TaxMath.calculateRoundedTaxValue(new Money(0.8), 0.1));
		assertEquals(new Money(0.10), TaxMath.calculateRoundedTaxValue(new Money(0.9), 0.1));
		assertEquals(new Money(0.10), TaxMath.calculateRoundedTaxValue(new Money(0.95), 0.1));
		assertEquals(new Money(0.10), TaxMath.calculateRoundedTaxValue(new Money(1.0), 0.1));
		
		assertEquals(new Money(0.15), TaxMath.calculateRoundedTaxValue(new Money(1.05), 0.1));
		assertEquals(new Money(0.15), TaxMath.calculateRoundedTaxValue(new Money(1.1), 0.1));
		assertEquals(new Money(0.15), TaxMath.calculateRoundedTaxValue(new Money(1.2), 0.1));
		assertEquals(new Money(0.15), TaxMath.calculateRoundedTaxValue(new Money(1.3), 0.1));
		assertEquals(new Money(0.15), TaxMath.calculateRoundedTaxValue(new Money(1.4), 0.1));
		assertEquals(new Money(0.15), TaxMath.calculateRoundedTaxValue(new Money(1.45), 0.1));
		assertEquals(new Money(0.15), TaxMath.calculateRoundedTaxValue(new Money(1.5), 0.1));
		
		assertEquals(new Money(0.20), TaxMath.calculateRoundedTaxValue(new Money(1.55), 0.1));
		assertEquals(new Money(0.20), TaxMath.calculateRoundedTaxValue(new Money(1.6), 0.1));
		assertEquals(new Money(0.20), TaxMath.calculateRoundedTaxValue(new Money(1.7), 0.1));
		assertEquals(new Money(0.20), TaxMath.calculateRoundedTaxValue(new Money(1.8), 0.1));
		assertEquals(new Money(0.20), TaxMath.calculateRoundedTaxValue(new Money(1.9), 0.1));
		assertEquals(new Money(0.20), TaxMath.calculateRoundedTaxValue(new Money(2.0), 0.1));
		
		assertEquals(new Money(0.25), TaxMath.calculateRoundedTaxValue(new Money(2.1), 0.1));
		assertEquals(new Money(0.25), TaxMath.calculateRoundedTaxValue(new Money(2.2), 0.1));
		assertEquals(new Money(0.25), TaxMath.calculateRoundedTaxValue(new Money(2.3), 0.1));
		
	}
	
}
