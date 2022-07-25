package com.wayne.micrometer.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DemoService {
    Counter visitCounter;
    Gauge gauge;
    public DemoService(MeterRegistry registry) {
        visitCounter = Counter.builder("visit_counter")
                .description("Number of visits to the site")
                .register(registry);

        gauge = Gauge.builder("number_gauge", this::gaugeValue)
                .description("test")
                .register(registry);
    }

    public String visit() {
        visitCounter.increment();
        return "Hello World!";
    }

    public Double gaugeValue(){
        return 0D;
    }

}
