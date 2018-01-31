package com.hlee.scratch.designpattern.sandwich.ex;

public abstract class Sandwich implements SandwichInf {

    private String description;

    public Sandwich(String desc) {
        this.description = desc;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
