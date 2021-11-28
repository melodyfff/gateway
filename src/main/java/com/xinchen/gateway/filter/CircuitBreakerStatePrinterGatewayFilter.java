package com.xinchen.gateway.filter;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.collection.Seq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 断路器状态监测
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2021/11/28 13:27
 */
public class CircuitBreakerStatePrinterGatewayFilter implements GatewayFilter {
    private static final Logger log = LoggerFactory.getLogger(CircuitBreakerStatePrinterGatewayFilter.class);
    private final ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory;

    public CircuitBreakerStatePrinterGatewayFilter(ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory) {
        this.reactiveResilience4JCircuitBreakerFactory = reactiveResilience4JCircuitBreakerFactory;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        if (null != reactiveResilience4JCircuitBreakerFactory.getCircuitBreakerRegistry()) {
            reactiveResilience4JCircuitBreakerFactory.getCircuitBreakerRegistry().getAllCircuitBreakers();
            Seq<CircuitBreaker> allCircuitBreakers = reactiveResilience4JCircuitBreakerFactory.getCircuitBreakerRegistry().getAllCircuitBreakers();
            allCircuitBreakers.forEach(breaker->{
                log.info("CircuitBreaker-[{}] state : {}",breaker.getName(),breaker.getState());
            });
        }

        // 继续执行后面的逻辑
        return chain.filter(exchange);
    }
}
