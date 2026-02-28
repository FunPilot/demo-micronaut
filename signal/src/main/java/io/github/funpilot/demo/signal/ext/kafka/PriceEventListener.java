package io.github.funpilot.demo.signal.ext.kafka;

import io.github.funpilot.demo.signal.Application;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class PriceEventListener {
    private static final Logger log = LoggerFactory.getLogger(PriceEventListener.class);

    @Topic(Application.KAFKA_TOPIC_PRICE_DEMO)
    @KafkaListener(offsetReset = OffsetReset.LATEST, groupId = "default")
    public void receive(@KafkaKey String key, PriceEvent event) {
        log.info("Received event, key:{}, price:{}", key, event.last());
    }
}
