package com.xinchen.gateway.route.respository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.synchronizedMap;

/**
 *
 * 从本地route.json文件中读取配置信息
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/23 16:04
 */
public final class FakeFileDB {
    public static final Map<String, RouteDefinition> ROUTES = synchronizedMap(
            new LinkedHashMap<>());

    static final Gson GSON = new Gson();

    static {
        load();
    }



    public static void load(){
        ClassPathResource classPathResource = new ClassPathResource("route.json");
        try {
            List<RouteDefinition> routeDefinition = GSON.fromJson(new InputStreamReader(classPathResource.getInputStream(), StandardCharsets.UTF_8), new TypeToken<List<RouteDefinition>>() {}.getType());
            routeDefinition.forEach((x)-> ROUTES.put(x.getId(), x));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
