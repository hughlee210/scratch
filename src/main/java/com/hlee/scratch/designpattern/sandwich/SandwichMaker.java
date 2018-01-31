package com.hlee.scratch.designpattern.sandwich;

public class SandwichMaker {

    public static void main(String[] args) {

        Sandwich sandwich1 = new WhiteBreadSandwich("White Bread Sandwich");
        System.out.printf("Price of %s is $%.2f %n", sandwich1.getDescription(), sandwich1.getPrice());

        Sandwich cheeseSandwich = new CheeseDecorator(sandwich1);
        System.out.printf("Price of %s is $%.2f %n", cheeseSandwich.getDescription(), cheeseSandwich.getPrice());
    }
}
