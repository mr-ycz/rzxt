package com.ycz.service;

import com.ycz.dao.MenuMapper;
import com.ycz.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Set<String> queryAllPermissionByUsername(String username) {
        Set<String> perms = menuMapper.selectAllPerms(username);

        Set<String> permissions = new HashSet<>();

        for (String perm : perms) {
            if (perm==null){
                continue;
            }
            String[] split = perm.split(",");
            permissions.addAll(Arrays.asList(split));
        }

        return permissions;
    }

    @Override
    public List<Menu> queryAllMenuByUsername(String username) {
        return menuMapper.selectAllMenu(username);
    }

    @Override
    public List<Menu> queryOneMenuByUsername(String username) {
        return menuMapper.selectOneMenu(username);
    }

    @Override
    public List<Menu> queryMenuOneChild(Integer menuId, String username) {
        return menuMapper.selectMenuOneChild(menuId,username);
    }

    @Override
    public List<Menu> queryAllMenu() {
        return menuMapper.selectAllMenus();
    }

    @Override
    public void addMenuRole(Integer roleId, List<Integer> menus) {
        for (Integer menu : menus) {
            menuMapper.insertMenu(roleId, menu);
        }
    }

    @Override
    public List<Integer> queryMenuIdByRoleId(Integer roleId) {
        return menuMapper.selectMenuByRoleId(roleId);
    }

    @Override
    public void updateMenuByRole(Integer roleId, List<Integer> menus) {
        //先将角色菜单表中roleId的信息全部删除
        menuMapper.deleteMenuByRoleId(roleId);
        //再给roleId添加新的菜单
        for (Integer menu : menus) {
            menuMapper.insertMenu(roleId, menu);
        }
    }

    @Override
    public void deleteMenuByRoleId(Integer roleId) {
        menuMapper.deleteMenuByRoleId(roleId);
    }
}
