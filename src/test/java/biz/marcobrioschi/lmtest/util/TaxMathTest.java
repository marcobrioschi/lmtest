package biz.marcobrioschi.lmtest.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import biz.marcobrioschi.lmtest.util.TaxMath;

public class TaxMathTest {

	@Test
	public void TaxMath_whenCheckCalculateRoundedTaxValue() {
		
		assertEquals(0.10, TaxMath.calculateRoundedTaxValue(0.7, 0.1), 0.0);
		assertEquals(0.10, TaxMath.calculateRoundedTaxValue(0.8, 0.1), 0.0);
		assertEquals(0.10, TaxMath.calculateRoundedTaxValue(0.9, 0.1), 0.0);
		assertEquals(0.10, TaxMath.calculateRoundedTaxValue(0.95, 0.1), 0.0);
		assertEquals(0.10, TaxMath.calculateRoundedTaxValue(1.0, 0.1), 0.0);
		
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.05, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.1, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.2, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.3, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.4, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.45, 0.1), 0.0);
		assertEquals(0.15, TaxMath.calculateRoundedTaxValue(1.5, 0.1), 0.0);
		
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(1.55, 0.1), 0.0);
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(1.6, 0.1), 0.0);
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(1.7, 0.1), 0.0);
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(1.8, 0.1), 0.0);
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(1.9, 0.1), 0.0);
		assertEquals(0.20, TaxMath.calculateRoundedTaxValue(2.0, 0.1), 0.0);
		
		assertEquals(0.25, TaxMath.calculateRoundedTaxValue(2.1, 0.1), 0.0);
		assertEquals(0.25, TaxMath.calculateRoundedTaxValue(2.2, 0.1), 0.0);
		assertEquals(0.25, TaxMath.calculateRoundedTaxValue(2.3, 0.1), 0.0);
		
	}
	
	@Test
	public void TaxMath_whenCheckRoundToTaxPrecision() {
		assertEquals(12345, TaxMath.roundToTaxPrecision(12345), 0.0);
		assertEquals(1234.5, TaxMath.roundToTaxPrecision(1234.5), 0.0);
		assertEquals(123.45, TaxMath.roundToTaxPrecision(123.45), 0.0);
		assertEquals(12.35, TaxMath.roundToTaxPrecision(12.345), 0.0);
		assertEquals(1.23, TaxMath.roundToTaxPrecision(1.2345), 0.0);
		assertEquals(0.12, TaxMath.roundToTaxPrecision(0.12345), 0.0);
		assertEquals(0.01, TaxMath.roundToTaxPrecision(0.012345), 0.0);
		assertEquals(0.00, TaxMath.roundToTaxPrecision(0.0012345), 0.0);
	}
	
}
