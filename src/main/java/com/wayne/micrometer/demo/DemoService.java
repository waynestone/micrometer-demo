package com.wayne.micrometer.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    Counter visitCounter;

    public DemoService(MeterRegistry registry) {
        visitCounter = Counter.builder("visit_counter")
                .description("Number of visits to the site")
                .register(registry);
    }

    public String visit() {
        visitCounter.increment();
        return "Hello World!";
    }

}
