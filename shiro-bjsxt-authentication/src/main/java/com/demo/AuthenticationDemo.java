package com.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Authentication 示例
 *
 * shiro.ini 文件是模拟从数据库获取验证信息
 *
 * 需要手动导入 org.apache.shiro.mgt.SecurityManager;否则报错
 *
 * Created by lwx on 2019/5/25.
 */
public class AuthenticationDemo {
    public static void main(String[] args) {
        // 构建SecurityManager 工厂，IniSecurityManagerFactory 可以从ini文件中初始化SecurityManager环境
        Factory<SecurityManager> factory = new
                IniSecurityManagerFactory("classpath:shiro.ini");
        //通过工厂获得SecurityManager 实例
        SecurityManager securityManager = factory.getInstance();
        //将securityManager 设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //获取subject 实例
        Subject subject = SecurityUtils.getSubject();
        //创建用户名,密码身份验证Token
        UsernamePasswordToken token = new
                UsernamePasswordToken("zhangsan", "1111");
        try {
            //登录，即身份验证
            subject.login(token);
            if (subject.isAuthenticated()) {
                System.out.println("用户授权成功");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //身份认证失败
            //...
        }
        //退出
        subject.logout();
    }
}
