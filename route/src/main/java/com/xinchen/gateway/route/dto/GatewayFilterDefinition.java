package com.xinchen.gateway.route.dto;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 创建过滤器模型
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/23 13:53
 */
@Data
public class GatewayFilterDefinition {
    /**Filter Name*/
    private String name;
    /**对应的路由规则*/
    private Map<String, String> args = new LinkedHashMap<>();
}
