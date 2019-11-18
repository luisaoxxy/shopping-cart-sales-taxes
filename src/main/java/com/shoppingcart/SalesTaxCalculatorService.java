package com.shoppingcart;

import com.shoppingcart.dto.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@Service
public class SalesTaxCalculatorService {

	private static Set<String> exemptItems =  new HashSet<>();
	final static double ROUND_RATE =20;
	final static double RATE = 0.1;
	final static double IMPORT_RATE = 0.05;

	public SalesTaxCalculatorService() {
		exemptItems.add("book");
		exemptItems.add("chocolate");
		exemptItems.add("pills");
	}

	public boolean isExempt(String name){
		var result = false;
		for(String exemptItem: exemptItems){
			if(name.contains(exemptItem)){
				result = true;
				break;
			}
		}
		return result;
	}

	public double calculateTaxes(Item item) {
		var itemRate = (item.isImported() ? IMPORT_RATE : 0) + (item.isExempt() ? 0 : RATE);
		double salesTax = BigDecimal.valueOf(Math.ceil(itemRate * item.getPrice() * ROUND_RATE)/ ROUND_RATE).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return salesTax * item.getAmount();
	}
}
