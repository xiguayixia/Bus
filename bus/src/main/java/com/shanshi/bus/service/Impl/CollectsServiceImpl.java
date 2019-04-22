package com.shanshi.bus.service.Impl;

import com.shanshi.bus.dao.CollectsDao;
import com.shanshi.bus.model.Collects;
import com.shanshi.bus.service.CollectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectsServiceImpl implements CollectsService {
    @Autowired
    private CollectsDao collectsDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Collects record) {
        return collectsDao.insert(record);
    }

    @Override
    public int insertSelective(Collects record) {
        return 0;
    }

    @Override
    public Collects selectByPrimaryKey(Integer id) {
        return collectsDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Collects> selectByUserId(Integer id) {
        return collectsDao.selectByUserId(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Collects record) {
        return collectsDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Collects record) {
        return collectsDao.updateByPrimaryKey(record);
    }
}
