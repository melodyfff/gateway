package com.xinchen.gateway;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 *
 * resilience4j RateLimiter 测试
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2021/11/28 17:04
 */
class RateLimiterTest {

    @Test
    void hello(){

        // The following example shows how to restrict the calling rate of some method to be not higher than 1 request/second.

        // Create a custom RateLimiter configuration
        RateLimiterConfig config = RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(100))
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(1)
                .build();
        // Create a RateLimiter
        RateLimiter rateLimiter = RateLimiter.of("backendName", config);

        // Decorate your call to BackendService.doSomething()
        Supplier<String> restrictedSupplier = RateLimiter
                .decorateSupplier(rateLimiter, ()-> "ok");

        // First call is successful
        Try<String> firstTry = Try.ofSupplier(restrictedSupplier);
        assertThat(firstTry.isSuccess()).isTrue();

        // Second call fails, because the call was not permitted
        Try<String> secondTry = Try.ofSupplier(restrictedSupplier);
        assertThat(secondTry.isFailure()).isTrue();
        assertThat(secondTry.getCause()).isInstanceOf(RequestNotPermitted.class);
    }
}