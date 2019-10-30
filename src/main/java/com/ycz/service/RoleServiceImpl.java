package com.ycz.service;

import com.ycz.dao.RoleMapper;
import com.ycz.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> queryAllRolenameByUsername(String username) {
        return roleMapper.selectAllRole(username);
    }

    @Override
    public List<Integer> queryAllRoleIdByUserId(Integer userId) {
        return roleMapper.selectRoleIdByUserId(userId);
    }

    @Override
    public List<Role> queryAllRole() {
        return roleMapper.selectAll();
    }

    @Override
    public void addRole(List<Integer> roles, Integer userId) {
        for (Integer role : roles) {
            roleMapper.insertRole(role,userId);
        }
    }

    @Override
    public List<Integer> queryRoleByUserId(Integer userId) {
        return roleMapper.selectRoleIdByUserId(userId);
    }

    @Override
    public void updateRole(List<Integer> roles, Integer userId) {
        for (Integer role : roles) {
            roleMapper.updateRole(role, userId);
        }
    }

    @Override
    public void deleteRoleByUserId(Integer userId) {
        roleMapper.deleteRoleByuserId(userId);
    }
}
