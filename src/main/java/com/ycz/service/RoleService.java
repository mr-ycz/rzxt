package com.ycz.service;

import com.ycz.pojo.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<String> queryAllRolenameByUsername(String username);

    List<Integer> queryAllRoleIdByUserId(Integer userId);

    List<Role> queryAllRole();

    void addRole(List<Integer> roles, Integer userId);

    List<Integer> queryRoleByUserId(Integer userId);

    void updateRole(List<Integer> roles, Integer userId);

    void deleteRoleByUserId(Integer userId);

    Integer queryCount();

    List<Role> queryRolesByPage(String order, Integer limit, Integer offset);

    void addARole(Role role);

    Role queryRoleByRoleId(Integer roleId);

    void updateARole(Role role);

    void deleteRoleByRoleId(Integer roleId);
}
