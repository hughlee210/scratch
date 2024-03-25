package com.hlee.scratch.corejava.designpattern.pubsub;

import java.util.ArrayList;
import java.util.List;

public class PubSub {

    public static void main(String[] args) {
        Publisher publisher = new ConcretePublisher();

        Subscriber subscriber1 = new ConcreteSubscriber("Subscriber1");
        Subscriber subscriber2 = new ConcreteSubscriber("Subscriber2");

        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        publisher.publish("Hello, World!");
    }
}

interface Publisher {
    void subscribe(Subscriber subscriber);
    void unSubscribe(Subscriber subscriber);

    void publish(String message);
}

interface Subscriber {
    void receive(String message);
}

class ConcretePublisher implements Publisher {

    private List<Subscriber> subscribers = new ArrayList<>();
    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unSubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void publish(String message) {
        for (Subscriber subscriber : subscribers) {
            subscriber.receive(message);
        }
    }
}

class ConcreteSubscriber implements Subscriber {

    private String name;

    public ConcreteSubscriber(String name) {
        this.name = name;
    }
    @Override
    public void receive(String message) {
        System.out.println(name + " received message: " + message);
    }
}

