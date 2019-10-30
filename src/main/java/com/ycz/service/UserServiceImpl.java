package com.ycz.service;

import com.ycz.dao.UserMapper;
import com.ycz.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByName(String username) {
        return userMapper.selectUserByname(username);
    }

    @Override
    public List<User> queryAllUser(String order, Integer limit, Integer offset) {
        return userMapper.selectAll(order,limit,offset);
    }

    @Override
    public Integer queryAllCount() {
        return userMapper.selectAllCount();
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User queryUserById(Integer userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUserByUserId(Integer userId) {
        userMapper.deleteUserByuserId(userId);
    }
}
