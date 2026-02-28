package io.github.funpilot.demo.mktdata.controller;

import io.github.funpilot.demo.mktdata.model.Exchange;
import io.github.funpilot.demo.mktdata.model.Price;
import io.github.funpilot.demo.mktdata.repo.PriceRepo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Controller("/v1/price")
@ExecuteOn(TaskExecutors.BLOCKING)
public class PriceController {
    private static final Logger log = LoggerFactory.getLogger(PriceController.class);

    @Inject
    private PriceRepo repo;

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "Welcome to market data app";
    }


    @Get("/last")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Price> lastPrice(String symbol, Exchange exchange) {
        log.info("Request for last price: {} {}", symbol, exchange);
        Optional<Price> price = repo.findLatest(symbol, exchange);
        if (price.isPresent()) {
            return HttpResponse.ok(price.get());
        } else {
            return HttpResponse.notFound();
        }
    }
}
