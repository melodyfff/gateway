package com.xinchen.gateway.route.common.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建路由模型
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/23 13:53
 */
@Data
public class GatewayRouteDefinition {
    /**路由的Id*/
    private String id;
    /**路由断言集合配置*/
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();
    /**路由过滤器集合配置*/
    private List<GatewayFilterDefinition> filters = new ArrayList<>();
    /**路由规则转发的目标uri*/
    private String uri;
    /**路由执行的顺序*/
    private int order = 0;
}
