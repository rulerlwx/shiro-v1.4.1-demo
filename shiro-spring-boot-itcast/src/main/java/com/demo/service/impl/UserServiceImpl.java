package com.demo.service.impl;

import com.demo.domain.Users;
import com.demo.mapper.UsersMapper;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lwx on 2019/6/3.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users getUserByName(String username) {
        return usersMapper.getUserByName(username);
    }
}
