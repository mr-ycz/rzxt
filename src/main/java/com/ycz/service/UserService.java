package com.ycz.service;

import com.ycz.pojo.User;

import java.util.List;

public interface UserService {
    User queryUserByName(String username);

    List<User> queryAllUser(String order, Integer limit, Integer offset);

    Integer queryAllCount();

    Integer addUser(User user);

    User queryUserById(Integer userId);

    void updateUser(User user);

    void deleteUserByUserId(Integer userId);
}
