package cn.wolfcode.shiro.web.controller;

import cn.wolfcode.shiro.realm.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @RequestMapping("")
    public String index() throws  Exception{
        System.out.println("执行了员工列表....");
        return "employee";
    }
    @RequestMapping("/save")
    @RequiresPermissions("employee:save")
    @PermissionName("员工保存")
    public String save() throws  Exception{
        System.out.println("执行了员工保存....");
        return "employee";
    }

    @RequestMapping("/edit")
    @RequiresPermissions("employee:edit")
    @PermissionName("员工编辑")
    public String edit() throws  Exception{
        System.out.println("执行了员工编辑....");
        return "employee";
    }

    @RequestMapping("/delete")
    @RequiresPermissions("employee:delete")
    @PermissionName("员工删除")
    public String delete() throws  Exception{
        System.out.println("执行了员工删除....");
        return "employee";
    }
}
