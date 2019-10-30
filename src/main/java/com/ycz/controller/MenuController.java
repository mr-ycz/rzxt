package com.ycz.controller;

import com.ycz.pojo.Menu;
import com.ycz.service.MenuService;
import com.ycz.service.RoleService;
import com.ycz.service.UserService;
import com.ycz.utils.R;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/menu")
@CrossOrigin("http://localhost:8080")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/user")
    @ResponseBody
    public R user(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //根据用户信息查询用户顶级菜单
        List<Menu> menuList=menuService.queryOneMenuByUsername(username);

        //根据用户名获取用户所有权限
        Set<String> perms=menuService.queryAllPermissionByUsername(username);

        //封装一级菜单
        for (Menu menu : menuList) {
            List<Menu> menuOneChildList=menuService.queryMenuOneChild(menu.getMenuId(), username);
            menu.setList(menuOneChildList);
        }

        return R.ok().put("menuList", menuList).put("permissions", perms);
    }

    @RequestMapping("/select")
    @ResponseBody
    public R select(){
        List<Menu> menuList=menuService.queryAllMenu();
        return R.ok().put("menuList", menuList);
    }
}
