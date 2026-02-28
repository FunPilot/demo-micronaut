package io.github.funpilot.demo.signal;

import io.micronaut.runtime.Micronaut;

public class Application {
    public static final String KAFKA_TOPIC_PRICE_DEMO = "topic-price-demo";

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}