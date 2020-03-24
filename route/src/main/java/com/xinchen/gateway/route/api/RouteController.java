package com.xinchen.gateway.route.api;

import com.xinchen.gateway.route.common.dto.GatewayFilterDefinition;
import com.xinchen.gateway.route.common.dto.GatewayPredicateDefinition;
import com.xinchen.gateway.route.common.dto.GatewayRouteDefinition;
import com.xinchen.gateway.route.dynamic.DynamicRouteService;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/23 13:08
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    private DynamicRouteService dynamicRouteService;

    public RouteController(DynamicRouteService dynamicRouteService) {
        this.dynamicRouteService = dynamicRouteService;
    }


    //增加路由
    @PostMapping("/add")
    public String add(@RequestBody GatewayRouteDefinition gwdefinition) {
        String flag = "fail";
        try {
            RouteDefinition definition = assembleRouteDefinition(gwdefinition);
            flag = this.dynamicRouteService.add(definition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    //删除路由
    @DeleteMapping("/routes/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        try {
            return this.dynamicRouteService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //更新路由
    @PostMapping("/update")
    public String update(@RequestBody GatewayRouteDefinition gwdefinition) {
        RouteDefinition definition = assembleRouteDefinition(gwdefinition);
        return this.dynamicRouteService.update(definition);
    }

    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gwdefinition) {
        RouteDefinition definition = new RouteDefinition();
        definition.setId(gwdefinition.getId());
        definition.setOrder(gwdefinition.getOrder());

        //设置断言
        List<PredicateDefinition> pdList=new ArrayList<>();
        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList=gwdefinition.getPredicates();
        for (GatewayPredicateDefinition gpDefinition: gatewayPredicateDefinitionList) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        definition.setPredicates(pdList);

        //设置过滤器
        List<FilterDefinition> filters = new ArrayList<>();
        List<GatewayFilterDefinition> gatewayFilters = gwdefinition.getFilters();
        for(GatewayFilterDefinition filterDefinition : gatewayFilters){
            FilterDefinition filter = new FilterDefinition();
            filter.setName(filterDefinition.getName());
            filter.setArgs(filterDefinition.getArgs());
            filters.add(filter);
        }
        definition.setFilters(filters);

        URI uri;
        if(gwdefinition.getUri().startsWith("http")){
            uri = UriComponentsBuilder.fromHttpUrl(gwdefinition.getUri()).build().toUri();
        }else{
            // uri为 lb://consumer-service 时使用下面的方法
            uri = URI.create(gwdefinition.getUri());
        }
        definition.setUri(uri);
        return definition;
    }
}
