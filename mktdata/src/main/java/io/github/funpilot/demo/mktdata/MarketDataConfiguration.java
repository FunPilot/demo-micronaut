package io.github.funpilot.demo.mktdata;

import io.micronaut.context.annotation.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("mktdata")
public interface MarketDataConfiguration {
    List<String> getWatchList();
    boolean isEnableKafka();
}
