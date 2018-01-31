package com.hlee.scratch.designpattern.sandwich;

import java.math.BigDecimal;

public abstract class Sandwich {

    protected String description;

    public Sandwich() {
    }

    public Sandwich(String description) {
        this.description = description;
    }

    String getDescription() {
        return description;
    }

    public abstract BigDecimal getPrice();
}
