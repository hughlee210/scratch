package com.hlee.scratch.designpattern.sandwich.ex;

import java.math.BigDecimal;

public class WhiteBreadSandwich extends Sandwich {

    public WhiteBreadSandwich(String desc) {
        super(desc);
    }

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.valueOf(3.0d);
    }
}
