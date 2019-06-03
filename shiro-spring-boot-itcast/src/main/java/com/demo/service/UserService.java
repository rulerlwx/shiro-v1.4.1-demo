package com.demo.service;

import com.demo.domain.Users;

/**
 * Created by lwx on 2019/6/3.
 */
public interface UserService {
    Users getUserByName(String username);
}
