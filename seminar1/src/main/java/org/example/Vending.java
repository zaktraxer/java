package org.example;

import java.util.ArrayList;
import java.util.List;

public class Vending {
    List<Product> list = new ArrayList<>();
    void addProduct(Product product) {
        list.add(product);
    }
    Product getProduct(String name){
        for (Product product: list
             ) {
            if (name.equals(product.name)){
                return product;
            }

        }
        return null;
    }
}
