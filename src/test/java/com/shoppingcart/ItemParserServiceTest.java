package com.shoppingcart;

import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

public class ItemParserServiceTest {

	private ShoppingCartParserService itemParserService = new ShoppingCartParserService(new SalesTaxCalculatorService());
	private final DecimalFormat formatter = new DecimalFormat("##0.00");


	@Test
	public void parseInput1() {
		var shoppingCart = itemParserService.parseShoppingCart("src/test/resources/input1.txt");
		Assert.assertEquals("1,50",formatter.format(shoppingCart.getSalesTaxes()));
		Assert.assertEquals("29,83",formatter.format(shoppingCart.getTotal()));
	}

	@Test
	public void parseInput2() {
		var shoppingCart = itemParserService.parseShoppingCart("src/test/resources/input2.txt");
		Assert.assertEquals("7,65",formatter.format(shoppingCart.getSalesTaxes()));
		Assert.assertEquals("65,15",formatter.format(shoppingCart.getTotal()));
	}

	@Test
	public void parseInput3() {
		var shoppingCart = itemParserService.parseShoppingCart("src/test/resources/input3.txt");
		Assert.assertEquals("6,70",formatter.format(shoppingCart.getSalesTaxes()));
		Assert.assertEquals("74,68",formatter.format(shoppingCart.getTotal()));
	}

	@Test
	public void parseInput4() {
		var shoppingCart = itemParserService.parseShoppingCart("src/test/resources/input4.txt");
		Assert.assertEquals("3,00",formatter.format(shoppingCart.getSalesTaxes()));
		Assert.assertEquals("31,33",formatter.format(shoppingCart.getTotal()));
	}
	@Test
	public void parseInputError() {
		var shoppingCart = itemParserService.parseShoppingCart("src/test/resources/inputError.txt");
		Assert.assertEquals(0,shoppingCart.getSalesTaxes(),0);
		Assert.assertEquals(0,shoppingCart.getTotal(),0);
	}

}
