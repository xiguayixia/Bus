package com.shanshi.bus.service;

import com.shanshi.bus.model.Collects;

import java.util.List;

public interface CollectsService {
    int deleteByPrimaryKey(Integer id);

    int insert(Collects record);

    int insertSelective(Collects record);

    Collects selectByPrimaryKey(Integer id);

    List<Collects> selectByUserId(Integer id);

    int updateByPrimaryKeySelective(Collects record);

    int updateByPrimaryKey(Collects record);
}
