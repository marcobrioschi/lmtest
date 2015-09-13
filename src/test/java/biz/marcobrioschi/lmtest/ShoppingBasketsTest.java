package biz.marcobrioschi.lmtest;

import static org.junit.Assert.assertEquals;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Test;
import org.junit.runner.RunWith;

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
	
}
