package com.ycz.controller;

import com.ycz.pojo.Role;
import com.ycz.pojo.User;
import com.ycz.service.MenuService;
import com.ycz.service.RoleService;
import com.ycz.service.UserService;
import com.ycz.utils.R;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin("http://localhost:8080")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    @RequestMapping("/roles")
    @ResponseBody
    public R roles(){
        List<Role> roleList=roleService.queryAllRole();

        return R.ok().put("roles", roleList);
    }

    @RequestMapping("/role/list")
    @ResponseBody
    public R role(String order, Integer limit, Integer offset){
        List<Role> roleList=roleService.queryRolesByPage(order,limit,offset);
        Integer total=roleService.queryCount();
        return R.ok().put("total", total).put("rows", roleList);
    }

    @RequestMapping("/role/save")
    @ResponseBody
    public R save(@RequestBody Role role){

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.queryUserByName(username);

        role.setCreateUserId(user.getUserId());
        role.setCreateTime(new Date());

        //添加角色
        roleService.addARole(role);
        //获取新增角色的id
        Integer roleId = role.getRoleId();

        //新增角色菜单关联
        menuService.addMenuRole(roleId,role.getMenus());

        return R.ok();
    }

    @RequestMapping("/role/info/{roleId}")
    @ResponseBody
    public R info(@PathVariable("roleId") Integer roleId){

        //根据角色id查询角色
        Role role=roleService.queryRoleByRoleId(roleId);
        //根据角色id查询对应菜单id
        List<Integer> menus=menuService.queryMenuIdByRoleId(roleId);

        role.setMenus(menus);

        return R.ok().put("role", role);
    }

    @RequestMapping("/role/update")
    @ResponseBody
    public R update(@RequestBody Role role){

        //更新菜单角色表
        menuService.updateMenuByRole(role.getRoleId(),role.getMenus());
        //更新角色表
        roleService.updateARole(role);

        return R.ok();
    }

    @RequestMapping("/role/del/{roleId}")
    @ResponseBody
    public R del(@PathVariable("roleId") Integer roleId){

        menuService.deleteMenuByRoleId(roleId);
        roleService.deleteRoleByRoleId(roleId);

        return R.ok();
    }

}
