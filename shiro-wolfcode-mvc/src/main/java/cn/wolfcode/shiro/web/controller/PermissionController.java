package cn.wolfcode.shiro.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@Controller
public class PermissionController {

    @Autowired
    private RequestMappingHandlerMapping rmhm;

    @RequestMapping("/reload")
    public String reload() throws  Exception{
        return "main";
    }

}
