package com.virtuslab.internship.BasketAPI;
import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SpringBootApplication
@RestController
public class BasketApiApplication {

	private Basket basket;

	public static void main(String[] args) {
		SpringApplication.run(BasketApiApplication.class, args);
	}


	@RequestMapping(value = "/basket", method = RequestMethod.POST)
	public void postBasket(@RequestBody List<Product> productList){
		this.basket = new Basket();
		try{
			for(var prod : productList){
				basket.addProduct(prod);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage() + ": Invalid product data");
		}
	}


	@RequestMapping(value = "/receipt", method = RequestMethod.GET)
	public Receipt getReceipt(){
		ReceiptGenerator receiptGen = new ReceiptGenerator();
		try{
			TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();
			FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();
			return tenPercentDiscount.apply(fifteenPercentDiscount.apply(receiptGen.generate(basket)));
		}
		catch(Exception e){
			System.out.println(e.getMessage() + ": Specify the basket first at http://localhost:8080/basket using POST method");
		}
		return null;
	}

}
