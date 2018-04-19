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

    @RequestMapping(value = "/a" ,method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> jsp(){
        System.out.println(1);/*
        return demoService.sayHello();*/
        List<Menu> list = new ArrayList<>();

        Menu n1 = new Menu();
        n1.setMenuName("系统设置");
        n1.setMenuNo("01");
        n1.setParentId("0");
        Menu n2 = new Menu();
        n2.setMenuName("用户管理");
        n2.setMenuNo("0101");
        n2.setParentId("1");

        List<Menu> list2 = new ArrayList<>();
        Menu n3 = new Menu();
        n3.setMenuName("用户管理1");
        n3.setMenuNo("010101");
        n3.setParentId("1");
        n3.setUrl("fsr/sys/user/user.jsp");
        Menu n4 = new Menu();
        n4.setMenuName("用户管理2");
        n4.setMenuNo("010102");
        n4.setParentId("1");
        list2.add(n3);
        list2.add(n4);
        n2.setChildrenItems(list2);
        Menu n5 = new Menu();
        n5.setMenuName("部门定义");
        n5.setMenuNo("0102");
        n5.setParentId("1");
        Menu n6 = new Menu();
        n6.setMenuName("岗位定义");
        n6.setMenuNo("0103");
        n6.setParentId("1");
        Menu n7 = new Menu();
        n7.setMenuName("角色定义");
        n7.setMenuNo("0104");
        n7.setParentId("1");
        list.add(n1);
        list.add(n2);
        list.add(n5);
        list.add(n6);
        list.add(n7);
        return list;
    }

    @RequestMapping(value = "/form" , method = RequestMethod.POST)
    public Demo saveDemo(@ModelAttribute Demo demo){
        System.out.println(2);/*
        demoService.createDemo(demo);*/
        return demo;
    }

}
