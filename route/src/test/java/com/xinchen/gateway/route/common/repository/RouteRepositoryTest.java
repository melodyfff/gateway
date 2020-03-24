package com.xinchen.gateway.route.common.repository;

import com.xinchen.gateway.route.GatewayApplicationTests;
import com.xinchen.gateway.route.common.entity.Route;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/24 13:54
 */
public class RouteRepositoryTest extends GatewayApplicationTests {
    @Resource
    private RouteRepository repository;

    @Test
    public void test(){

        String json = "{\n" +
                "  \"id\": \"server1\",\n" +
                "  \"predicates\": [{\n" +
                "    \"name\": \"Path\",\n" +
                "    \"args\": {\"_genkey_0\":\"/first\"}\n" +
                "  }],\n" +
                "  \"filters\": [\n" +
                " {\n" +
                "    \"name\": \"StripPrefix\",\n" +
                "    \"args\": {\"_genkey_0\":\"2\"}\n" +
                "  },{\n" +
                "  \t\"name\": \"Authorize\",\n" +
                "    \"args\": {\"_genkey_0\":\"false\"}\n" +
                "  }\n" +
                "  \t],\n" +
                "  \"uri\": \"http://www.baidu.com1\",\n" +
                "  \"order\": 0\n" +
                "}";

        final Route route = new Route();
        route.setRouteId("hello2");
        route.setEnable(true);
        route.setUri("http://bing.com");
        route.setJson(json);

        repository.save(route);

    }
}