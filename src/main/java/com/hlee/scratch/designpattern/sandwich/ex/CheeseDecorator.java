package com.hlee.scratch.designpattern.sandwich.ex;

import java.math.BigDecimal;

public class CheeseDecorator implements SandwichDecorator {

    private SandwichInf sandwich;

    public CheeseDecorator(SandwichInf sandwich) {
        this.sandwich = sandwich;
    }

    @Override
    public String getDescription() {
        return sandwich.getDescription() + ", cheese";
    }

    @Override
    public BigDecimal getPrice() {
        return sandwich.getPrice().add(BigDecimal.valueOf(0.25d));
    }

}
