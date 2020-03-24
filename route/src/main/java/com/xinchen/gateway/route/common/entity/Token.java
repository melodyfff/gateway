package com.xinchen.gateway.route.common.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/3/24 14:11
 */
@Entity
@Data
@Table(name = "gateway_token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String token;

    private String description;

    @ManyToMany(cascade = { CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "gateway_token_route",
            joinColumns = {@JoinColumn(name = "token_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "route_id", referencedColumnName = "id")}
    )
    private Set<Route> routeList = new HashSet<>();
}
