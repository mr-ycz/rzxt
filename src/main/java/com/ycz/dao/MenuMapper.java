package com.ycz.dao;

import com.ycz.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface MenuMapper {
    Set<String> selectAllPerms(@Param("username") String username);

    List<Menu> selectAllMenu(@Param("username") String username);

    List<Menu> selectOneMenu(@Param("username") String username);

    List<Menu> selectMenuOneChild(@Param("menuId") Integer menuId, @Param("username") String username);

    List<Menu> selectAllMenus();

    void insertMenu(@Param("roleId") Integer roleId,@Param("menu") Integer menu);

    List<Integer> selectMenuByRoleId(Integer roleId);

    void deleteMenuByRoleId(Integer roleId);
}
