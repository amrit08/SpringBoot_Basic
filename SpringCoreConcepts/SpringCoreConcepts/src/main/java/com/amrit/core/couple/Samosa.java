package com.amrit.core.couple;

public class Samosa {

    String name;

    public Samosa(String name) {
        System.out.println("this is" + name);
        this.name = name;
    }

    public void eat() {
        System.out.println("Samosa is spicy");
        System.out.println(name);
    }
}
