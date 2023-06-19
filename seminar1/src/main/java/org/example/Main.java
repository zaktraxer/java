package org.example;


public class Main {
    public static void main(String[] args){
        Vending vending = new Vending();

        Product product1 = new Product();

        product1.name = "Apple";

        vending.addProduct(product1);
        System.out.println(vending.getProduct(product1.name));

    }
}