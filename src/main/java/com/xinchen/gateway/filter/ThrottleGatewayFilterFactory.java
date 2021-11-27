package com.xinchen.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2021/11/27 16:42
 */
@Component
public class ThrottleGatewayFilterFactory extends AbstractGatewayFilterFactory<ThrottleGatewayFilterFactory.Config> {
    private static final Logger logger = LoggerFactory.getLogger(ThrottleGatewayFilterFactory.class);
    private final ThrottleGatewayFilter throttleGatewayFilter;
    public ThrottleGatewayFilterFactory(ThrottleGatewayFilter throttleGatewayFilter) {
        super(Config.class);
        this.throttleGatewayFilter = throttleGatewayFilter;
        logger.info("Loaded GatewayFilterFactory [Throttle]");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (config.enabled){
                logger.info(">>> Throttle run .");
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    public static class Config {
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
