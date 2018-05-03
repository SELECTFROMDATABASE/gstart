package com.gstart.demo.web.controller;

import com.gstart.common.base.BaseController;
import com.gstart.demo.dao.pojo.Demo;
import com.gstart.demo.dao.pojo.User;
import org.springframework.web.bind.annotation.*;
import com.gstart.demo.dao.pojo.Menu;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class DemoController extends BaseController {/*
    @Autowired
    private DemoService demoService;*/

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> jsp() {
        System.out.println(1);/*
        return demoService.sayHello();*/
        List<Menu> list = new ArrayList<>();

        Menu n1 = new Menu();
        n1.setMenuName("系统设置");
        n1.setMenuNo("01");
        n1.setMainId("1");
        n1.setParentId("0");
        n1.setIconClass("gear-b");

        List<Menu> list3 = new ArrayList<>();

        Menu n4 = new Menu();
        n4.setMenuName("用户管理");
        n4.setMenuNo("0101");
        n4.setParentId("1");
        n4.setUrl("gstart/sys/user/user");

        Menu n5 = new Menu();
        n5.setMenuName("部门定义");
        n5.setMenuNo("0102");
        n5.setParentId("1");
        n5.setUrl("gstart/sys/department/department");
        Menu n6 = new Menu();
        n6.setMenuName("岗位定义");
        n6.setMenuNo("0103");
        n6.setParentId("1");
        n6.setUrl("gstart/sys/position/position");
        Menu n7 = new Menu();
        n7.setMenuName("角色定义");
        n7.setMenuNo("0104");
        n7.setParentId("1");
        n7.setUrl("gstart/sys/role/role");
        list3.add(n4);
        list3.add(n5);
        list3.add(n6);
        list3.add(n7);
        n1.setChildrenItems(list3);
        Menu n8 = new Menu();
        n8.setMenuName("资源管理");
        n8.setMenuNo("02");
        n8.setParentId("0");
        n8.setIconClass("upload");
        List<Menu> list4 = new ArrayList<>();

        Menu n9 = new Menu();
        n9.setMenuName("文章管理");
        n9.setMenuNo("0201");
        n9.setParentId("02");
        list4.add(n9);
        Menu n10 = new Menu();
        n10.setMenuName("视频管理");
        n10.setMenuNo("0202");
        n10.setParentId("02");
        list4.add(n10);
        n8.setChildrenItems(list4);

        list.add(n1);
        list.add(n8);
        return list;
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public Demo saveDemo(@ModelAttribute Demo demo) {
        System.out.println(2);/*
        demoService.createDemo(demo);*/
        return demo;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUser() {
        List<User> list = new ArrayList<>();
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();
        User u4 = new User();
        u1.setAccount("123");
        u2.setAccount("234");
        u3.setAccount("345");
        u4.setAccount("456");
        u1.setId("1");
        u2.setId("2");
        u3.setId("3");
        u4.setId("4");
        u1.setName("test1");
        u2.setName("test2");
        u3.setName("test3");
        u4.setName("test4");
        u1.setStatus("1");
        u2.setStatus("2");
        u3.setStatus("2");
        u4.setStatus("2");
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        return list;
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getNav", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> getNav(){
        System.out.println(2);/*
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
        n4.setUrl("gstart/sys/user/user");

        Menu n5 = new Menu();
        n5.setMenuName("部门定义");
        n5.setMenuNo("0102");
        n5.setParentId("1");
        n5.setUrl("gstart/sys/department/department");
        Menu n6 = new Menu();
        n6.setMenuName("岗位定义");
        n6.setMenuNo("0103");
        n6.setParentId("1");
        n6.setUrl("gstart/sys/position/position");
        Menu n7 = new Menu();
        n7.setMenuName("角色定义");
        n7.setMenuNo("0104");
        n7.setParentId("1");
        n7.setUrl("gstart/sys/role/role");
        list3.add(n4);
        list3.add(n5);
        list3.add(n6);
        list3.add(n7);
        n1.setChildrenItems(list3);

        Menu n8 = new Menu();
        n8.setMenuName("资源管理");
        n8.setMenuNo("02");
        n8.setParentId("0");
        Menu n9 = new Menu();
        n9.setMenuName("文章管理");
        n9.setMenuNo("02");
        n9.setParentId("0");
        Menu n10 = new Menu();
        n10.setMenuName("视频管理");
        n10.setMenuNo("02");
        n10.setParentId("0");

        list.add(n1);
        list.add(n8);
        return list;
    }

}
