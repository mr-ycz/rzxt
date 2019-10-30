package com.ycz.service;

import com.ycz.pojo.Menu;

import java.util.List;
import java.util.Set;

public interface MenuService {
    Set<String> queryAllPermissionByUsername(String username);

    List<Menu> queryAllMenuByUsername(String username);

    List<Menu> queryOneMenuByUsername(String username);

    List<Menu> queryMenuOneChild(Integer menuId, String username);

    List<Menu> queryAllMenu();

    void addMenuRole(Integer roleId, List<Integer> menus);

    List<Integer> queryMenuIdByRoleId(Integer roleId);

    void updateMenuByRole(Integer roleId, List<Integer> menus);

    void deleteMenuByRoleId(Integer roleId);
}
