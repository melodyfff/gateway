package com.xinchen.gateway.route.common.repository;

import com.xinchen.gateway.route.common.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/24 13:50
 */
public interface UserRepository extends CrudRepository<User,Integer> {
}
