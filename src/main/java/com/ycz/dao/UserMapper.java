package com.ycz.dao;

import com.ycz.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectUserByname(@Param("username") String username);

    List<User> selectAll(@Param("order") String order,@Param("limit") Integer limit,@Param("offset") Integer offset);

    Integer selectAllCount();

    Integer insertUser(User user);

    User selectUserById(Integer userId);

    void updateUser(User user);

    void deleteUserByuserId(@Param("userId") Integer userId);
}
