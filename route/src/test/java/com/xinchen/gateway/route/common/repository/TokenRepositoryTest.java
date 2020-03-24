package com.xinchen.gateway.route.common.repository;

import com.xinchen.gateway.route.GatewayApplicationTests;
import com.xinchen.gateway.route.common.entity.Token;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/24 15:39
 */
public class TokenRepositoryTest extends GatewayApplicationTests {

    @Resource
    private RouteRepository routeRepository;
    @Resource
    private TokenRepository tokenRepository;


    @Test
    public void test(){
        final Token token = new Token();
        token.setToken("test");
        token.getRouteList().add(routeRepository.findById(1).get());
        tokenRepository.save(token);
    }

    @Test
    public void testAdd(){
        final Token token = tokenRepository.findById(2).get();
        token.getRouteList().add(routeRepository.findById(5).get());
        tokenRepository.save(token);
    }

    @Test
    public void testDel(){
        tokenRepository.deleteById(2);
    }
}