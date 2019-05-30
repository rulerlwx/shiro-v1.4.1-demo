package cn.wolfcode.shiro.realm;

import cn.wolfcode.shiro.dao.IUserDAO;
import cn.wolfcode.shiro.domain.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;

/**
 * Created by lwx on 2019/5/29.
 */
public class UserRealm extends AuthorizingRealm {

    private IUserDAO userDAO;

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User user = (User) principals.getPrimaryPrincipal();

        ArrayList<String> permissionList = new ArrayList<>();

        //静态授权
        if ("zhangsan".equals(user.getUsername())) {
            permissionList.add("employee:edit");
        } else if ("admin".equals(user.getUsername())) {
            permissionList.add("*:*");
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();

        //从数据库查用户信息
        User user = userDAO.getUserByUsername(username);

        if (user == null) {
            return null;
        }

        //第一个参数是 user，不是username ——20190529
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());

        return info;
    }

    @Override
    public String getName() {
        return "UserRealm";
    }

}
