package com.xinchen.gateway.filter;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2021/11/28 13:30
 */
@Component
public class CircuitBreakerStatePrinterGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    private final ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory;

    public CircuitBreakerStatePrinterGatewayFilterFactory(ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory) {
        this.reactiveResilience4JCircuitBreakerFactory = reactiveResilience4JCircuitBreakerFactory;
    }

    @Override
    public String name() {
        return "CircuitBreakerStatePrinter";
    }

    @Override
    public GatewayFilter apply(Object config) {
        return new CircuitBreakerStatePrinterGatewayFilter(reactiveResilience4JCircuitBreakerFactory);
    }
}
