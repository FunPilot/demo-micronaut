package io.github.funpilot.demo.signal;

import io.micronaut.context.annotation.Property;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals("signal", appName);
    }
}
