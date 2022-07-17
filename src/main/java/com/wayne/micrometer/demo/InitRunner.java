package com.wayne.micrometer.demo;

import com.sun.net.httpserver.HttpServer;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@Component
@Order(value = 0)
public class InitRunner implements ApplicationRunner {

    @Autowired
    PrometheusMeterRegistry prometheusRegistry;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        //-------micrometer
        // http://localhost:9091/prometheus
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(9091), 0);
            server.createContext("/prometheus", httpExchange -> {
                String response = prometheusRegistry.scrape();
                httpExchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });

            new Thread(server::start).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
