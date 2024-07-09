package org.example.beans;

public class CartService {

    private String name;
    public void createCart(){
        System.out.println("one cart is created");
        System.out.println("name is : "+name);

    }

    public void setName(String name) {
        this.name = name;
    }
}
