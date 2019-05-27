package com.demo.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;

/**
 * 授权
 *
 * Created by lwx on 2019/5/26.
 */
public class PermissionRealm extends AuthorizingRealm {
    //当前realm名字
    @Override
    public String getName() {
        return "PermissionRealm";
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();//SimpleAuthenticationInfo的第一个参数：username

        //模拟从数据库中查询出来的数据
        ArrayList<String> roles = new ArrayList<>();//从数据库中查到的角色集合
        ArrayList<String> permissions = new ArrayList<>();//从数据库中查到的权限集合
        roles.add("role1");
        permissions.add("user:delete");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //入参token：登录时包装的UsernamePasswordToken

        String username = (String) token.getPrincipal();

        if (!username.equals("zhangsan")) {//模拟用户不存在
            return null;
        }

        String credential = "123456";

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                username, credential,getName());
        return info;
    }
}
