package com.demo;

import static org.junit.Assert.assertTrue;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * 测试Shiro认证
 */
public class ShiroTest {

    /**
     * 测试角色、权限
     */
    @Test
    public void testRolePermissionByRealm() {
        //1、登录
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-permission-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken("zhangsan", "123456");
        subject.login(token);

        //是否拥有角色
        System.out.println(subject.hasRole("role1"));

        //是否有权限
        System.out.println(subject.isPermitted("user:create"));

        //登出
        //subject.logout();
    }

    /**
     * 测试权限
     */
    @Test
    public void testHashPermission() {
        //1、登录
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken("zhangsan", "123456");
        subject.login(token);

        //是否有权限，方式一
        System.out.println(subject.isPermitted("user:create"));
        System.out.println(subject.isPermitted("create"));
        System.out.println(subject.isPermittedAll("user:create","user:update"));

        //是否有权限，方式二
        subject.checkPermission("user:createddd");

        //登出
        //subject.logout();
    }

    /**
     * 测试角色
     */
    @Test
    public void testHashRoles() {
        //1、登录
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken("zhangsan", "123456");
        subject.login(token);

        //2、判断是否拥有角色：方式一
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2","role3")));
        System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1","role2","role3"))));

        //2、判断是否拥有角色：方式二
        subject.checkRole("role2");//如果没有角色，将抛出 UnauthorizedException

        //登出
        //subject.logout();
    }


    /**
     * 测试用md5加密的realm
     */
    @Test
    public void testPasswordRealm() {
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token =
                new UsernamePasswordToken("zhangsan", "123456");
        try {
            //6、登录
            subject.login(token);

        } catch (Exception ex) {
            //异常处理
            throw ex;
        }

        System.out.println("是否登录成功：" + subject.isAuthenticated());

        //登出
        subject.logout();

        System.out.println("是否登录成功：" + subject.isAuthenticated());
    }

    /**
     * 测试自定义realm
     */
    @Test
    public void testMyRealm() {
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token =
                new UsernamePasswordToken("zhangsan", "123456");
        try {
            //6、登录
            subject.login(token);

        } catch (Exception ex) {
            //异常处理
            throw ex;
        }

        System.out.println("是否登录成功：" + subject.isAuthenticated());

        //登出
        subject.logout();

        System.out.println("是否登录成功：" + subject.isAuthenticated());
    }

    /**
     * 测试第一个ini认证
     */
    @Test
    public void testIniAuthentication() {
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token =
                new UsernamePasswordToken("zhangsan", "123456");
        try {
            //6、登录
            subject.login(token);

        } catch (Exception ex) {
            //异常处理
        }

        System.out.println("是否登录成功：" + subject.isAuthenticated());

        //登出
        subject.logout();

        System.out.println("是否登录成功：" + subject.isAuthenticated());
    }
}
