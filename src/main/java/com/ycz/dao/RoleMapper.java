package com.ycz.dao;

import com.ycz.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface RoleMapper {

    Set<String> selectAllRole(String username);

    List<Integer> selectRoleIdByUserId(Integer userId);

    List<Role> selectAll();

    void insertRole(@Param("role") Integer role,@Param("userId") Integer userId);

    void updateRole(@Param("role") Integer role,@Param("userId") Integer userId);

    void deleteRoleByuserId(@Param("userId") Integer userId);

    Integer selectCount();

    List<Role> selectRoleByPage(@Param("order") String order,@Param("limit") Integer limit,@Param("offset") Integer offset);

    void insertARole(Role role);

    Role selectRoleByRoleId(Integer roleId);

    void updateArole(Role role);

    void deleteRoleByRoleId(Integer roleId);
}
