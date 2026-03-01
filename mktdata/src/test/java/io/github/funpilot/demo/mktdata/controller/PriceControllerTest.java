package io.github.funpilot.demo.mktdata.controller;

import io.github.funpilot.demo.mktdata.model.Exchange;
import io.github.funpilot.demo.mktdata.model.Price;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.micronaut.http.HttpStatus.CREATED;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PriceControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void testIndex() {
        HttpRequest<?> request = HttpRequest.GET("/v1/price").accept(MediaType.TEXT_PLAIN);
        String actual = client.toBlocking().retrieve(request);
        assertEquals("Welcome to market data app", actual);
    }

    @Test
    @Order(1)
    public void testSavePrice() {
        Price price = new Price();
        price.setSymbol("UT-REST-001");
        price.setExchange(Exchange.NYSE);
        price.setTradeDate(LocalDate.now());
        price.setLast(new BigDecimal("100.12"));
        price.setVolume(9999);
        HttpRequest<?> request = HttpRequest.POST("/v1/price", price);
        HttpResponse<?> response = client.toBlocking().exchange(request);
        assertEquals(CREATED, response.getStatus());
    }

    @Test
    @Order(2)
    public void testLastPrice() {
        HttpRequest<?> request2 = HttpRequest.GET("/v1/price/last?symbol=UT-REST-001&exchange=NYSE").accept(MediaType.APPLICATION_JSON);
        Price actual = client.toBlocking().retrieve(request2, Price.class);
        assertEquals("UT-REST-001", actual.getSymbol());
        assertEquals(Exchange.NYSE, actual.getExchange());
    }
}
