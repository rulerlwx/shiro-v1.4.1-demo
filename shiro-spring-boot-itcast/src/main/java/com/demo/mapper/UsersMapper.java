package com.demo.mapper;

import com.demo.domain.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by lwx on 2019/6/3.
 */
@Mapper
public interface UsersMapper {
    Users getUserByName(String username);
}
