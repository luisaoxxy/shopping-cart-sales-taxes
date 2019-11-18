package com.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.text.DecimalFormat;

@SpringBootApplication
public class ShoppingCartApplication implements CommandLineRunner {

	@Autowired
	private ShoppingCartParserService itemParserService;
    private final DecimalFormat formatter = new DecimalFormat("##0.00");

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(args.length == 0){
			System.out.println("At least one fileName is required");
			System.exit(0);
		}else{
			for (String fileName: args){
				var shoppingCart = itemParserService.parseShoppingCart(new ClassPathResource(fileName).getFile().getAbsolutePath());
                System.out.println("Output: " +fileName);
                shoppingCart.getItems().forEach(item ->
                    System.out.println(item.getAmount() + " " + item.getName() + ": " + formatter.format(item.getPrice()))
                );
                System.out.println("Sales Taxes: " + formatter.format(shoppingCart.getSalesTaxes()));
                System.out.println("Total: " + formatter.format(shoppingCart.getTotal()));
                System.out.println();
			}
		}
		System.exit(0);

	}
	}
