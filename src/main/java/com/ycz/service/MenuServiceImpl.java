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
}
