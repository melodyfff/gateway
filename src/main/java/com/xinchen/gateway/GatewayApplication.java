package com.xinchen.gateway;

import com.xinchen.gateway.filter.ThrottleGatewayFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.concurrent.TimeUnit;

/**
 *
 *
 * see: https://github.com/spring-cloud/spring-cloud-gateway/blob/main/spring-cloud-gateway-sample/src/main/java/org/springframework/cloud/gateway/sample/GatewaySampleApplication.java
 * <p>
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2021/11/27 13:43
 */
@SpringBootApplication
public class GatewayApplication {
    @Value("${test.uri:http://httpbin.org:80}")
    String uri;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //  curl http://localhost:8080/get -H "Host:www.throttle.org"
        return builder.routes()
                .route(r -> r.order(-1)
                        .host("**.throttle.org").and().path("/get")
                        .filters(f -> f
                                .filter(new ThrottleGatewayFilter()
                                        .setCapacity(1)
                                        .setRefillTokens(1)
                                        .setRefillPeriod(10)
                                        .setRefillUnit(TimeUnit.SECONDS)))
                        .uri(uri)
                )
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> testFunRouterFunction() {
        return RouterFunctions
                .route(
                        RequestPredicates.path("/testfun"),
                        request -> ServerResponse.ok().body(BodyInserters.fromValue("hello"))
                );
    }
}
