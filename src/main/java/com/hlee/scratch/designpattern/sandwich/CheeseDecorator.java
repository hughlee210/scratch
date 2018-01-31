package com.hlee.scratch.designpattern.sandwich;

import java.math.BigDecimal;

public class CheeseDecorator extends SandwichDecorator {

    public CheeseDecorator(Sandwich sandwich) {
        super(sandwich);
    }

    @Override
    String getDescription() {
        return getSandwich().getDescription() + " with Cheese";
    }

    @Override
    public BigDecimal getPrice() {
        return getSandwich().getPrice().add(new BigDecimal("0.10"));
    }

}
