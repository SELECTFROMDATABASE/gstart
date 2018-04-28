package com.gstart.demo.web.controller;

import com.gstart.common.base.BaseController;
import com.gstart.demo.dao.pojo.Demo;
import com.gstart.demo.dao.pojo.Menu;
import com.gstart.demo.rpc.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class DemoController extends BaseController {/*
    @Autowired
    private DemoService demoService;*/

    @CrossOrigin(origins="*",maxAge=3600)
    @RequestMapping(value = "/a" ,method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> jsp(){
        System.out.println(1);/*
        return demoService.sayHello();*/
        List<Menu> list = new ArrayList<>();

        Menu n1 = new Menu();
        n1.setMenuName("系统设置");
        n1.setMenuNo("01");
        n1.setMainId("1");
        n1.setParentId("0");

        List<Menu> list3 = new ArrayList<>();

        Menu n4 = new Menu();
        n4.setMenuName("用户管理");
        n4.setMenuNo("0101");
        n4.setParentId("1");
        n4.setUrl("gstart/sys/user/user.jsp");

        Menu n5 = new Menu();
        n5.setMenuName("部门定义");
        n5.setMenuNo("0102");
        n5.setParentId("1");
        n5.setUrl("gstart/sys/user/user.jsp");
        Menu n6 = new Menu();
        n6.setMenuName("岗位定义");
        n6.setMenuNo("0103");
        n6.setParentId("1");
        n6.setUrl("gstart/sys/user/user.jsp");
        Menu n7 = new Menu();
        n7.setMenuName("角色定义");
        n7.setMenuNo("0104");
        n7.setParentId("1");
        n7.setUrl("gstart/sys/user/user.jsp");
        list3.add(n4);
        list3.add(n5);
        list3.add(n6);
        list3.add(n7);
        n1.setChildrenItems(list3);
        list.add(n1);
        return list;
    }

    @RequestMapping(value = "/form" , method = RequestMethod.POST)
    public Demo saveDemo(@ModelAttribute Demo demo){
        System.out.println(2);/*
        demoService.createDemo(demo);*/
        return demo;
    }

}
