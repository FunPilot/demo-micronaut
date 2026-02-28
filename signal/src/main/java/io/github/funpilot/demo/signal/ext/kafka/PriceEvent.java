package io.github.funpilot.demo.signal.ext.kafka;

import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Serdeable
public record PriceEvent(
        String symbol,
        String exchange,
        LocalDate tradeDate,
        BigDecimal open,
        BigDecimal close,
        BigDecimal high,
        BigDecimal low,
        BigDecimal bid,
        BigDecimal ask,
        BigDecimal last,
        long volume
) { }