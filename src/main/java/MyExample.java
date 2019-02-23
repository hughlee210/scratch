package com.sie.kodama.udscommonsapp.stats.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyExample {

    public static void main(String[] args) {

        Name name1 = new Name("heonkoo", "lee");
        Name name2 = new Name("kristy", "nam");

        Person p1 = new Person(name1);
        Person p2 = new Person(name2);

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

        System.out.println(list);

        list.stream()
                .filter(person ->  person.name.firstName.equals("heonkoo"))
                .forEach(person -> person.name.firstName = person.name.firstName.toUpperCase());

        System.out.println(list);

        List<Person> results = list.stream().filter(person -> person.name.firstName.equals("kristy")).collect(Collectors.toList());
        results.forEach(person -> person.name.firstName = person.name.firstName.toUpperCase());

        System.out.println(list);
    }

    static class Person {

        Name name;

        public Person(Name name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                   "name=" + name +
                   '}';
        }
    }

    static class Name {

        String firstName;
        String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Name{" +
                   "firstName='" + firstName + '\'' +
                   ", lastName='" + lastName + '\'' +
                   '}';
        }
    }
}

