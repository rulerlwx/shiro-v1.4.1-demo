package com.demo.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by lwx on 2019/5/26.
 */
public class MyRealm extends AuthorizingRealm {

    //当前realm名字
    @Override
    public String getName() {
        return "MyRealm";
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //入参token：登录时包装的UsernamePasswordToken

        String username = (String) token.getPrincipal();

        String credential = "123456";//模拟数据库中获取的密码是123456

        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(username, credential, getName());
        return info;
    }
}
