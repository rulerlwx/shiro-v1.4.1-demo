package cn.wolfcode.shiro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @RequestMapping("")
    public String index() throws  Exception{
        System.out.println("执行了部门列表....");
        return "department";
    }
    @RequestMapping("/save")
    public String save() throws  Exception{
        System.out.println("执行了部门保存....");
        return "department";
    }

    @RequestMapping("/edit")
    public String edit() throws  Exception{
        System.out.println("执行了部门编辑....");
        return "department";
    }

    @RequestMapping("/delete")
    public String delete() throws  Exception{
        System.out.println("执行了部门删除....");
        return "department";
    }
}
