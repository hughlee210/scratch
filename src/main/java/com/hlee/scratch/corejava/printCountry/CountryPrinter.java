package com.hlee.scratch.corejava.printCountry;

import java.util.LinkedHashSet;
import java.util.Set;

public class CountryPrinter {

    public static void main(String[] args) {

        CountryPrinter countryPrinter = new CountryPrinter();
        countryPrinter.addCountry(new Country("USA", 1));
        countryPrinter.addCountry(new Country("MEXICO", 2));
        countryPrinter.addCountry(new Country("KOREA", 3));
        countryPrinter.addCountry(new Country("CHINA", 4));
        countryPrinter.addCountry(new Country("JAPAN", 5));

        for (int i = 1; i <= 100; i++) {
            System.out.println(i + ". " + countryPrinter.getCountry());
        }

    }

    private Set<Country> countries = new LinkedHashSet<>();

    private void addCountry(Country country) {
        countries.add(country);
    }

    private Set<Country> getCountries() {
        return countries;
    }

    private String getCountry() {
        for (Country country : getCountries()) {
            if (country.isCallable()) {
                return country.getName();
            }
        }
        // now no callable countries left, so reset calledCount
        for (Country country : countries) {
            country.setCalledCount(0);
        }
        // countries.forEach(c -> c.setCalledCount(0));

        return getCountry();
    }

}
