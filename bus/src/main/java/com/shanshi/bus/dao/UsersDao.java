package com.shanshi.bus.dao;

import com.shanshi.bus.model.Users;

public interface UsersDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    Users selectByOpenId(String openid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}