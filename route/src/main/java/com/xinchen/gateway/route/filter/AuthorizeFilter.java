package com.xinchen.gateway.route.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * 自定义filter
 *
 * Filter分为{@link org.springframework.cloud.gateway.filter.GatewayFilter}和{@link GlobalFilter}
 *
 * 实现GlobalFilter接口，自动会对所有的路由起作用
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/19 16:52
 */
@Slf4j
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info(">>>>>> AuthorizeFilter");
//        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);
//        return response.setComplete();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
