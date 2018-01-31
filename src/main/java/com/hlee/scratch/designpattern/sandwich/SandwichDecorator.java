package com.hlee.scratch.designpattern.sandwich;

public abstract class SandwichDecorator extends Sandwich {

    private Sandwich sandwich;

    public SandwichDecorator(Sandwich sandwich) {
        this.sandwich = sandwich;
    }

    public Sandwich getSandwich() {
        return sandwich;
    }

    public void setSandwich(Sandwich sandwich) {
        this.sandwich = sandwich;
    }

}
