package io.github.funpilot.demo.mktdata.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Serdeable
@Entity
@Table(name = "price")
@IdClass(PriceKey.class)
public class Price {
    @Id
    private String symbol;
    @Id
    private Exchange exchange;
    @Id
    private LocalDate tradeDate;

    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal last;
    private long volume;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }
}