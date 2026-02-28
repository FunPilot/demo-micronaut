package io.github.funpilot.demo.mktdata.ext.kafka;

import io.github.funpilot.demo.mktdata.Application;
import io.github.funpilot.demo.mktdata.model.Price;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.scheduling.TaskExecutors;

@KafkaClient(value = "price-event-client", executor = TaskExecutors.BLOCKING)
public interface PriceEventProducer {

    @Topic(Application.KAFKA_TOPIC_PRICE_DEMO)
    void sendPriceEvent(@KafkaKey String key, Price event);
}
