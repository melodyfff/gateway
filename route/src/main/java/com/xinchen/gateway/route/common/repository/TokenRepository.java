package com.xinchen.gateway.route.common.repository;

import com.xinchen.gateway.route.common.entity.Token;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/24 15:38
 */
public interface TokenRepository extends CrudRepository<Token,Integer> {
}
