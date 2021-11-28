//package com.xinchen.gateway.config;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
//import io.github.resilience4j.timelimiter.TimeLimiterConfig;
//import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
//import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
//
///**
// *
// * 手动配置，实际可用yml配置文件替代
// * https://github.com/resilience4j/resilience4j#spring-boot
// *
// * 三个一般性状态
// * CLOSED：关闭状态，放过所有请求，记录请求状态。
// * OPEN：打开，异常请求达到阀值数量时，开启熔断，拒绝所有请求。
// * HALF_OPEN：半开，放开一定数量的请求，重新计算错误率。
// *
// * 两个特定状态
// * DISABLED：禁用
// * FORCED_OPEN：强开
// *
// *
// * @author Xin Chen (xinchenmelody@gmail.com)
// * @version 1.0
// * @date Created In 2021/11/28 13:00
// */
//@Configuration
//class ConfigCircuitBreaker {
//    @Bean
//    public ReactiveResilience4JCircuitBreakerFactory defaultCustomizer() {
//
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom() //
//                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED) // 滑动窗口的类型为时间窗口
//                .slidingWindowSize(10) // 时间窗口的大小为60秒
//                .minimumNumberOfCalls(5) // 在单位时间窗口内最少需要5次调用才能开始进行统计计算
//                .failureRateThreshold(50) // 在单位时间窗口内调用失败率达到50%后会启动断路器
//                .enableAutomaticTransitionFromOpenToHalfOpen() // 允许断路器自动由打开状态转换为半开状态
//                .permittedNumberOfCallsInHalfOpenState(5) // 在半开状态下允许进行正常调用的次数
//                .waitDurationInOpenState(Duration.ofSeconds(5)) // 断路器打开状态转换为半开状态需要等待60秒
//                .recordExceptions(Throwable.class) // 所有异常都当作失败来处理
//                .build();
//
//        ReactiveResilience4JCircuitBreakerFactory factory = new ReactiveResilience4JCircuitBreakerFactory();
//        factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(2000)).build())
//                .circuitBreakerConfig(circuitBreakerConfig).build());
//
//        return factory;
//    }
//}
