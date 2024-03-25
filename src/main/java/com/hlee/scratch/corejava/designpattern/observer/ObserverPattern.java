package com.hlee.scratch.corejava.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer concreteObserver1 = new ConcreteObserver();
        Observer concreteObserver2 = new ConcreteObserver();

        subject.attach(concreteObserver1);
        subject.attach(concreteObserver2);

        subject.notifiyAllObservers();
    }
}

interface Observer {
    void update();
}

class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifiyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

class ConcreteObserver implements Observer {

    @Override
    public void update() {
        System.out.println("Observer notified of changes");
    }
}
