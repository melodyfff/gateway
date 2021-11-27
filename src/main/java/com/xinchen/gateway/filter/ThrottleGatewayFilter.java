package com.xinchen.gateway.filter;

import org.isomorphism.util.TokenBucket;
import org.isomorphism.util.TokenBuckets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 *
 * Sample throttling filter. See https://github.com/bbeck/token-bucket
 * <p>
 *
 * see: https://github.com/spring-cloud/spring-cloud-gateway/blob/main/spring-cloud-gateway-sample/src/main/java/org/springframework/cloud/gateway/sample/ThrottleGatewayFilter.java
 * <p>
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2021/11/27 15:24
 */
@Component
public class ThrottleGatewayFilter implements GatewayFilter , Ordered {
    private static final Logger log = LoggerFactory.getLogger(ThrottleGatewayFilter.class);
    int capacity;

    int refillTokens;

    int refillPeriod;

    TimeUnit refillUnit;

    public int getCapacity() {
        return capacity;
    }

    public ThrottleGatewayFilter setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public int getRefillTokens() {
        return refillTokens;
    }

    public ThrottleGatewayFilter setRefillTokens(int refillTokens) {
        this.refillTokens = refillTokens;
        return this;
    }

    public int getRefillPeriod() {
        return refillPeriod;
    }

    public ThrottleGatewayFilter setRefillPeriod(int refillPeriod) {
        this.refillPeriod = refillPeriod;
        return this;
    }

    public TimeUnit getRefillUnit() {
        return refillUnit;
    }

    public ThrottleGatewayFilter setRefillUnit(TimeUnit refillUnit) {
        this.refillUnit = refillUnit;
        return this;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        TokenBucket tokenBucket = TokenBuckets.builder().withCapacity(capacity)
                .withFixedIntervalRefillStrategy(refillTokens, refillPeriod, refillUnit).build();

        // TODO: get a token bucket for a key
        log.info("TokenBucket capacity: " + tokenBucket.getCapacity());
        boolean consumed = tokenBucket.tryConsume();
        if (consumed) {
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return exchange.getResponse().setComplete();
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
