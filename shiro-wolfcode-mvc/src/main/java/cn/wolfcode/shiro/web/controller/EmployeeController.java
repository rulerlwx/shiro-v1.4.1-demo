package cn.wolfcode.shiro.web.controller;

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
    public String save() throws  Exception{
        System.out.println("执行了员工保存....");
        return "employee";
    }

    @RequestMapping("/edit")
    public String edit() throws  Exception{
        System.out.println("执行了员工编辑....");
        return "employee";
    }

    @RequestMapping("/delete")
    public String delete() throws  Exception{
        System.out.println("执行了员工删除....");
        return "employee";
    }
}
