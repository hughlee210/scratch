package com.hlee.scratch.examplePrintCountry;

public class Country {

    private String name;
    private long weight;
    private long calledCount;

    public Country(String name, long weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        calledCount++;
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getCalledCount() {
        return calledCount;
    }

    public void setCalledCount(long calledCount) {
        this.calledCount = calledCount;
    }

    public boolean isCallable() {
        return calledCount < weight;
    }

}
