package cn.wolfcode.shiro.web.controller;

import cn.wolfcode.shiro.dao.IPermissionDAO;
import cn.wolfcode.shiro.domain.Permission;
import cn.wolfcode.shiro.realm.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class PermissionController {

    //springmvc在启动时将所有标记有@RequestMapping的方法收集起来并封装到此对象
    @Autowired
    private RequestMappingHandlerMapping rmhm;

    @Autowired
    private IPermissionDAO permissionDAO;

    //将系统中所有的权限添加到数据库中
    @RequestMapping("/reload")
    public String reload() throws  Exception{
        //0、从数据库中查出所有权限表达式，然后对比，如果已经存在则跳过，如果不存在则添加
        List<String> resourcesList = permissionDAO.getAllResources();

        //1、获取所有标记有@RequestMapping的方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
        Collection<HandlerMethod> methods = handlerMethods.values();
        //2、遍历所有方法，判断是否有@RequiresPermissions标签
        for (HandlerMethod method : methods) {
            RequiresPermissions annotation = method.getMethodAnnotation(RequiresPermissions.class);
            if (annotation != null) {
                String permission = annotation.value()[0];//假设只有一个权限表达式

                //如果权限表达式已经存在，则跳过（不重复添加）
                if (resourcesList.contains(permission)) {
                    continue;
                }

                //3、如果有，解析权限表达式，封装成permission对象保存到permission表中
                Permission p = new Permission();
                p.setResource(permission);
                p.setName(method.getMethodAnnotation(PermissionName.class).value());

                //保存到数据表中
                permissionDAO.save(p);
            }
        }
        return "main";
    }
}
