package biz.marcobrioschi.lmtest.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import biz.marcobrioschi.lmtest.util.Money;

@RunWith(mockit.integration.junit4.JMockit.class)
public class MoneyTest {

	private void checkRounding(double startValue, double expectedValue) {
		Money money = new Money(startValue);
		assertEquals("Check rounding from " + startValue + " to " + expectedValue, expectedValue, money.getValueAsDouble(), 0.0);
	}
	
	@Test
	public void valueIsRoundedToNearestCent_whenICreateMoney() {
		checkRounding(0.7449, 0.74);
		checkRounding(0.7451, 0.75);
		checkRounding(0.7452, 0.75);
		checkRounding(0.714, 0.71);
		checkRounding(0.715, 0.72);
		checkRounding(0.716, 0.72);
		checkRounding(0.74, 0.74);
		checkRounding(0.75, 0.75);
		checkRounding(0.76, 0.76);
		checkRounding(0.4, 0.4);
		checkRounding(0.5, 0.5);
		checkRounding(0.6, 0.6);
	}

	@Test
	public void resultIsTheSum_whenIAddMoney() {
		
		Money result;
		
		Money moneyA = new Money(0.72);
		Money moneyB = new Money(20.52);
		Money moneyC = new Money(123);
		
		result = moneyA.add(moneyB);
		assertEquals("Check sum of A + B", 21.24, result.getValueAsDouble(), 0.0);
		
		result = moneyA.add(moneyC);
		assertEquals("Check sum of A + C", 123.72, result.getValueAsDouble(), 0.0);

		result = moneyB.add(moneyC);
		assertEquals("Check sum of B + C", 143.52, result.getValueAsDouble(), 0.0);
		
	}
	
}
