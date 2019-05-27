package com.demo.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 用md5对密码加密
 *
 * Created by lwx on 2019/5/26.
 */
public class PasswordRealm extends AuthorizingRealm {
    //当前realm名字
    @Override
    public String getName() {
        return "PasswordRealm";
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

        if (!username.equals("zhangsan")) {//模拟用户不存在
            return null;
        }

        String credential = "5cb022bfe7cab9f9f6b043d32e58e617";//模拟数据库中获取的密码是密文

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                username, credential, ByteSource.Util.bytes("zhangsan"),getName());//记得加盐值
        return info;
    }
}
