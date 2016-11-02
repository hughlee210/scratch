package com.hlee.scratch;

import java.util.LinkedHashSet;
import java.util.Set;

public class PrintCountryByWeight {

//  "USA" , 1333999
//  "MEXICO", 122
//  "KOREA", 1111
//  "CHINA", 122

    public static void main(String[] args) {
        PrintCountryByWeight test = new PrintCountryByWeight();

        for (int i = 0; i < 100; i++) {
            System.out.println(i + ". " + test.getCountry());
        }
    }

    static class CountryWeight {
        String name;
        long weight;
        long calledCount;

        public CountryWeight(String name, long weight) {
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

        public Long getWeight() {
            return weight;
        }

        public void setWeight(Long weight) {
            this.weight = weight;
        }

        public Long getCalledCount() {
            return calledCount;
        }

        public void setCalledCount(long calledCount) {
            this.calledCount = calledCount;
        }

        public boolean callable() {
            return weight - calledCount > 0;
        }

    }

    static Set<CountryWeight> cwSet = new LinkedHashSet<CountryWeight>();
    static {
        cwSet.add(new CountryWeight("USA", 1));
        cwSet.add(new CountryWeight("MEXICO", 1));
        cwSet.add(new CountryWeight("KOREA", 1));
        cwSet.add(new CountryWeight("CHINA", 1));
    }

    public String getCountry() {
        for (CountryWeight cw : cwSet) {
            if (cw.callable()) {
                return cw.getName();
            }
        }
        // no callable countries left
        for (CountryWeight cw : cwSet) {
            cw.setCalledCount(0);
        }

        return getCountry();
    }

}