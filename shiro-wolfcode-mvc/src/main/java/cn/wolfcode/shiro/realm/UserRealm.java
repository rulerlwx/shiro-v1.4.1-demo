package cn.wolfcode.shiro.realm;

import cn.wolfcode.shiro.dao.IPermissionDAO;
import cn.wolfcode.shiro.dao.IRoleDAO;
import cn.wolfcode.shiro.dao.IUserDAO;
import cn.wolfcode.shiro.domain.User;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
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
import java.util.List;

/**
 * Created by lwx on 2019/5/29.
 */
public class UserRealm extends AuthorizingRealm {
    @Setter
    private IUserDAO userDAO;
    @Setter
    private IRoleDAO roleDAO;
    @Setter
    private IPermissionDAO permissionDAO;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User user = (User) principals.getPrimaryPrincipal();

        List<String> permissionList = new ArrayList<>();
        List<String> roleList = new ArrayList<>();

        //静态授权
//        if ("zhangsan".equals(user.getUsername())) {
//            permissionList.add("employee:edit");
//        } else if ("admin".equals(user.getUsername())) {
//            permissionList.add("*:*");
//        }

        //动态授权
        if ("admin".equals(user.getUsername())) {
            //拥有所有权限
            permissionList.add("*:*");
            //拥有所有角色
            roleList = roleDAO.getAllRoleSn();
        } else {
            //根据用户id查询角色
            roleList = roleDAO.getRoleSnByUserId(user.getId());
            //根据用户id查询资源权限
            permissionList = permissionDAO.getPermissionResourceByUserId(user.getId());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleList);
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
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes("admin"),getName());

        return info;
    }

    @Override
    public String getName() {
        return "UserRealm";
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
