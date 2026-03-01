package io.github.funpilot.demo.mktdata.repo;

import io.github.funpilot.demo.mktdata.model.Exchange;
import io.github.funpilot.demo.mktdata.model.Price;
import io.github.funpilot.demo.mktdata.model.PriceKey;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@MicronautTest
public class PriceRepoTest {

    @Inject
    PriceRepo repo;

    @Test
    public void testSaveAndFind() {
        Price entity = new Price();
        entity.setSymbol("UT001");
        entity.setExchange(Exchange.NYSE);
        entity.setTradeDate(LocalDate.now());
        entity.setLast(new BigDecimal("100.12"));
        entity.setVolume(9999);
        repo.save(entity);

        Price actual = repo.findByKey(new PriceKey(entity.getSymbol(), entity.getExchange(), entity.getTradeDate())).orElseThrow();
        assertEquals(actual, entity);
    }

    @Test
    public void testFindLatest() {
        String symbol = "UT002";
        Exchange exchange = Exchange.NYSE;

        Price entity = new Price();
        entity.setSymbol(symbol);
        entity.setExchange(exchange);
        entity.setTradeDate(LocalDate.now());
        entity.setLast(new BigDecimal("100.12"));
        entity.setVolume(9999);
        repo.save(entity);

        Price entity2 = new Price();
        entity2.setSymbol(symbol);
        entity2.setExchange(exchange);
        entity2.setTradeDate(LocalDate.now().minusDays(1));
        entity2.setLast(new BigDecimal("99.98"));
        entity2.setVolume(9901);
        repo.save(entity2);

        Price actual = repo.findLatest(symbol, exchange).orElseThrow();
        assertEquals(actual, entity);
    }

    @Test
    public void testFindAll() {
        Price entity = new Price();
        entity.setSymbol("UT003");
        entity.setExchange(Exchange.NYSE);
        entity.setTradeDate(LocalDate.now());
        entity.setLast(new BigDecimal("100.12"));
        entity.setVolume(9999);
        repo.save(entity);

        Price entity2 = new Price();
        entity2.setSymbol("UT003B");
        entity2.setExchange(Exchange.NYSE);
        entity2.setTradeDate(LocalDate.now());
        entity2.setLast(new BigDecimal("83.88"));
        entity2.setVolume(8888);
        repo.save(entity2);

        List<Price> actual = repo.findAll();
        assertFalse(actual.isEmpty());
    }
}
