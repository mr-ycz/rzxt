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
}
