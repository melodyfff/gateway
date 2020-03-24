package com.xinchen.gateway.route.common.dto;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * 路由断言模型
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/23 13:53
 */
@Data
public class GatewayPredicateDefinition {
    /**断言对应的Name*/
    private String name;
    /**配置的断言规则*/
    private Map<String, String> args = new LinkedHashMap<>();
}
