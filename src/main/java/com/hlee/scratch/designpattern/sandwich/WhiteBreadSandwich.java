package com.hlee.scratch.designpattern.sandwich;

import java.math.BigDecimal;

public class WhiteBreadSandwich extends Sandwich {

    public WhiteBreadSandwich(String desc) {
        super(desc);
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("3.0");
    }
}
