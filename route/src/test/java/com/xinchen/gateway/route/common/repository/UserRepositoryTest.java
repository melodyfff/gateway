package com.xinchen.gateway.route.common.repository;

import com.xinchen.gateway.route.GatewayApplicationTests;
import com.xinchen.gateway.route.common.entity.User;
import org.junit.Test;

import javax.annotation.Resource;


/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/24 13:51
 */
public class UserRepositoryTest extends GatewayApplicationTests {

    @Resource
    private UserRepository repository;
    @Resource
    private RouteRepository routeRepository;
    @Resource
    private TokenRepository tokenRepository;
    @Test
    public void test(){
        final User user = new User();
        user.setUserNo("hello");
        user.getTokens().add(tokenRepository.findById(2).get());
        repository.save(user);
    }

    @Test
    public void testFind(){
        System.out.println(repository.findAll());
    }

    @Test
    public void testDel(){

        repository.deleteById(4);
    }
}