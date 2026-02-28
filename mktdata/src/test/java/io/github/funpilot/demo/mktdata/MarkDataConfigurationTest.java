package io.github.funpilot.demo.mktdata;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
public class MarkDataConfigurationTest {
    @Inject
    MarketDataConfiguration config;

    @Test
    public void testWatchList() {
        List<String> list = config.getWatchList();
        Assertions.assertNotNull(list);
    }
}
