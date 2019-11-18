package com.shoppingcart;

import com.shoppingcart.dto.Item;
import com.shoppingcart.dto.ShoppingCart;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShoppingCartParserService {

	private final SalesTaxCalculatorService salesTaxCalculator;
	private static final String ITEM_REGEX = "(?<amount>\\d+)\\s(?<name>(\\w+\\s)+)at\\s(?<price>\\d+.\\d+)";
    private final Pattern pattern;

	public ShoppingCartParserService(SalesTaxCalculatorService salesTaxCalculator) {
		this.salesTaxCalculator = salesTaxCalculator;
		this.pattern = Pattern.compile(ITEM_REGEX);
	}

	public ShoppingCart parseShoppingCart(String fileName){
        ShoppingCart shoppingCart = null;
	    try (BufferedReader in = new BufferedReader(new FileReader(fileName))){
            shoppingCart  = parseItem(in.lines());
        } catch (IOException e) {
            System.out.println("There was an error:" + e.getMessage());
        }
	    return shoppingCart;
    }

	private ShoppingCart parseItem(Stream<String> shoppingCart) {
        var items = new ArrayList<Item>();
        double salesTaxes = 0;
        double total = 0;
        for (String line: shoppingCart.collect(Collectors.toList())) {
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()){
                var name = matcher.group("name");
                var price = Double.valueOf(matcher.group("price"));
                var item = new Item.Builder().withName(name)
                        .withAmount(Integer.valueOf(matcher.group("amount")))
                        .withPrice(price)
                        .withExempt(salesTaxCalculator.isExempt(name))
                        .withImported(name.contains("imported")).build();
                var itemTax = salesTaxCalculator.calculateTaxes(item);
                item.setSaleTax(itemTax);
                items.add(item);
                salesTaxes += itemTax;
                total += item.getTotalPrice();
            }else{
                System.out.println("Incorrect format for line" + line);
            }
        }
		return new ShoppingCart(items,salesTaxes,total);
	}

}
