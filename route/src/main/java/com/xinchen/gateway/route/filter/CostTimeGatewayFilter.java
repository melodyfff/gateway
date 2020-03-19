package com.xinchen.gateway.route.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 *
 * Filter分为{@link GatewayFilter}和{@link GlobalFilter}
 *
 * 这个{@link GatewayFilter}是为了记录一次调用消耗时间
 *
 * 通过配置
 * <pre>
 *   cloud:
 *     gateway:
 *       routes:
 *         - id: server1
 *           uri: lb://server1
 *           predicates:
 *             - Path=/server1/**
 *           filters:
 *             - StripPrefix=1
 *             - Authorize=false
 *             - CostTime=true       # 配置这里开启
 * </pre>
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/19 17:55
 */
@Slf4j
public class CostTimeGatewayFilter implements GatewayFilter, Ordered {
    private static final String COUNT_START_TIME = "countStartTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(COUNT_START_TIME, Instant.now().toEpochMilli() );
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    if (null != exchange.getAttribute(COUNT_START_TIME)){
                        long startTime = exchange.getAttribute(COUNT_START_TIME);
                        long endTime=(Instant.now().toEpochMilli() - startTime);
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + endTime + "ms");
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
