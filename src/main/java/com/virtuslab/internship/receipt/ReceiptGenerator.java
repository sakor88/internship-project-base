package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        List<Product> productList = basket.getProducts();
        Set<Product> productSet = new HashSet<Product>(productList);
        for (var prod : productSet){
            receiptEntries.add(new ReceiptEntry(prod, (int) productList
                    .stream()
                    .filter(p -> p.name().equals(prod.name()))
                    .count()));
        }
        System.out.println(receiptEntries);
        return new Receipt(receiptEntries);
    }
}
