package com.shoppingcart;

import com.shoppingcart.dto.Item;
import org.junit.Assert;
import org.junit.Test;

public class SalesTaxCalculatorServiceTest {

	private final SalesTaxCalculatorService salesTaxCalculatorService =  new SalesTaxCalculatorService();

	@Test
	public void isExempt(){
		Assert.assertTrue(salesTaxCalculatorService.isExempt("book"));
		Assert.assertFalse(salesTaxCalculatorService.isExempt("music"));
		Assert.assertTrue(salesTaxCalculatorService.isExempt("chocolate bar"));
		Assert.assertTrue(salesTaxCalculatorService.isExempt("imported box of chocolates"));
		Assert.assertFalse(salesTaxCalculatorService.isExempt("imported bottle of perfume"));
		Assert.assertFalse(salesTaxCalculatorService.isExempt("bottle of perfume "));
		Assert.assertTrue(salesTaxCalculatorService.isExempt("packet of headache pills"));
		Assert.assertTrue(salesTaxCalculatorService.isExempt("box of imported chocolates "));
	}

	@Test
	public void calculateTaxes() {
	    var item = new Item.Builder().withAmount(1).withName("book").withPrice(12.49)
                .withExempt(true).withImported(false).build();
	    var salesTaxes = salesTaxCalculatorService.calculateTaxes(item);
	    Assert.assertEquals(0,salesTaxes,0);
	    item = new Item.Builder().withAmount(1).withName("music CD").withPrice(14.99)
                .withExempt(false).withImported(false).build();
	    salesTaxes = salesTaxCalculatorService.calculateTaxes(item);
	    Assert.assertEquals(1.5,salesTaxes,0);
	    item = new Item.Builder().withAmount(1).withName("imported box of chocolates").withPrice(10)
                .withExempt(true).withImported(true).build();
	    salesTaxes = salesTaxCalculatorService.calculateTaxes(item);
	    Assert.assertEquals(0.5,salesTaxes,0);
	    item = new Item.Builder().withAmount(1).withName("imported bottle of perfume").withPrice(47.5)
                .withExempt(false).withImported(true).build();
	    salesTaxes = salesTaxCalculatorService.calculateTaxes(item);
	    Assert.assertEquals(7.15,salesTaxes,0);
	}
}
