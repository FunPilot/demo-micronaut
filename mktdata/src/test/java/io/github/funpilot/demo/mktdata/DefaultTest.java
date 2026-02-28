package io.github.funpilot.demo.mktdata;

import io.micronaut.context.annotation.Property;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class DefaultTest {

    @Inject
    EmbeddedApplication<?> application;

    @Property(name = "micronaut.application.name")
    private String appName;

    @Test
    void testRun() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    public void testConfig() {
        Assertions.assertEquals("mktdata", appName);
    }
}
