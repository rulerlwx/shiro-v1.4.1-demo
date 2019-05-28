package com.demo.shiro.factory;

import java.util.LinkedHashMap;

/**
 * 从数据表中查出资源权限（这里是用静态模拟数据）
 *
 * 注意配置的顺序，因为有第一位置优先原则 —— 20190528
 *
 *
 * Created by lwx on 2019/5/26.
 */
public class FilterChainDefinitionMapBuilder {
    public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("/login.jsp", "anon");
        map.put("/shiro/login", "anon");
        map.put("/shiro/logout", "logout");
        map.put("/user.jsp", "authc,roles[user]");
        map.put("/admin.jsp", "authc,roles[admin]");
        map.put("/list.jsp", "user");

        map.put("/**", "authc");

        return map;
    }
}
