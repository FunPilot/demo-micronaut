package io.github.funpilot.demo.mktdata.model;

import java.time.LocalDate;

public record PriceKey(
        String symbol,
        Exchange exchange,
        LocalDate tradeDate
) { }