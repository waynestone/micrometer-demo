package com.wayne.micrometer.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.Callable;
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




        //jmx
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        Gauge.builder("jmx.test.task", mBeanServer, (s) -> {
            return this.safeDouble(() -> {
                return s.getAttribute(new ObjectName("jmxBean:name=task"), "Number");
            });
        }).register(registry);
    }

    public String visit() {
        visitCounter.increment();
        return "Hello World!";
    }

    public Double gaugeValue(){
        return 0D;
    }



    private double safeDouble(Callable<Object> callable) {
        try {
            return Double.parseDouble(callable.call().toString());
        } catch (Exception var3) {
            return 0.0D / 0.0;
        }
    }

}
