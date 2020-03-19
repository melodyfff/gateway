package com.xinchen.gateway.route.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * GatewayFilterFactory 实现方式，激活使用需要如下配置
 * <pre>
 *   cloud:
 *     gateway:
 *       routes:
 *         - id: server1
 *           uri: lb://server1
 *           predicates:
 *             - Path=/server1/**
 *           filters:
 *             - Authorize=true  # 开启使用
 * </pre>
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/19 17:31
 */
@Slf4j
@Component
public class AuthorizeGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizeGatewayFilterFactory.Config> {

    public AuthorizeGatewayFilterFactory() {
        super(Config.class);
        log.info("Loaded GatewayFilterFactory [Authorize]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(Config config) {
        if (config.enabled){
            log.info(">>>>>> [Authorize]");
            return ((exchange, chain) -> {
                ServerHttpRequest request = exchange.getRequest();
                HttpHeaders headers = request.getHeaders();
                ServerHttpResponse response = exchange.getResponse();
                log.info("{} enter [Authorize].",request.getURI());
                return chain.filter(exchange);
            });
        } else {
            return (exchange, chain)-> chain.filter(exchange);
        }
    }

    public static class Config {
        // 控制是否开启认证
        private boolean enabled;

        public Config() {}

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
