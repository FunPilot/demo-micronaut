package io.github.funpilot.demo.mktdata.service;

import io.github.funpilot.demo.mktdata.MarketDataConfiguration;
import io.github.funpilot.demo.mktdata.ext.kafka.PriceEventProducer;
import io.github.funpilot.demo.mktdata.model.Exchange;
import io.github.funpilot.demo.mktdata.model.Price;
import io.github.funpilot.demo.mktdata.repo.PriceRepo;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Singleton
public class PriceService {
    private static final Logger log = LoggerFactory.getLogger(PriceService.class);

    @Inject
    private ApplicationContext ctx;

    @Inject
    private MarketDataConfiguration config;

    @Inject
    private PriceRepo repo;

    @Inject
    private PriceEventProducer producer;

    @Requires(notEnv = Environment.TEST)
    @EventListener
    public void init(StartupEvent event) {
        log.info("Init for testing...");

        Price entity = new Price();
        entity.setSymbol("NVDA");
        entity.setExchange(Exchange.NYSE);
        entity.setTradeDate(LocalDate.now());
        entity.setLast(new BigDecimal("180.00"));
        entity.setVolume(9999);
        accept(entity);

        repo.findAll().forEach(p -> {
            log.info("Price saved, date:{}, symbol:{}, last:{}", p.getTradeDate(), p.getSymbol(), p.getLast());
        });
    }

    public void accept(Price price) {
        repo.save(price);
        if (config.isEnableKafka()) {
            String key = "%s-%s-%s".formatted(price.getSymbol(), price.getExchange(), price.getTradeDate());
            log.info("Sending event, key:{}", key);
            producer.sendPriceEvent(key, price);
        }
    }
}
