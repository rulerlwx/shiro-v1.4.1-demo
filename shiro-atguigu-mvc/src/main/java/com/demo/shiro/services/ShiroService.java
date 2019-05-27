package com.demo.shiro.services;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

import java.util.Date;

/**
 * Created by lwx on 2019/5/26.
 */
public class ShiroService {
    @RequiresRoles({"admin"})
    public void testMethod(){
        System.out.println("testMethod, time: " + new Date());

        Session session = SecurityUtils.getSubject().getSession();
        Object val = session.getAttribute("key");

        System.out.println("Service SessionVal: " + val);
    }
}
