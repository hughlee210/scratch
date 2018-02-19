package com.hlee.scratch.designpattern.sandwich.ex;

public class SandwichMaker {

    public static void main(String[] args) {

        SandwichInf whiteBreadSandwich = new WhiteBreadSandwich("White Bread Sandwich");
        System.out.printf("Price of %s: $%.2f %n", whiteBreadSandwich.getDescription(), whiteBreadSandwich.getPrice());

        SandwichInf whiteBreadSandwichWithCheese = new CheeseDecorator(whiteBreadSandwich);
        System.out.printf("Price of %s: $%.2f %n", whiteBreadSandwichWithCheese.getDescription(), whiteBreadSandwichWithCheese.getPrice());
    }
}
