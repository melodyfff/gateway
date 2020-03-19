package com.xinchen.gateway.route.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/19 17:51
 */
@Slf4j
@Component
public class CostTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<CostTimeGatewayFilterFactory.Config> {
    public CostTimeGatewayFilterFactory() {
        super(Config.class);
        log.info("Loaded GatewayFilterFactory [CostTime]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(Config config) {
        if (config.enabled){
            return new CostTimeGatewayFilter();
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
