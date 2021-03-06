package com.mksun.ucenter.service.impl;

import com.mksun.ucenter.dao.TestDao;
import com.mksun.commons.entity.Test;
import com.mksun.ucenter.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("TestService")
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;

    @Override
    public List<Test> queryTestList() {
        return testDao.queryTestList();
    }

    @Override
    public Test queryTestById(String id) {
        return testDao.queryTestById(id);
    }

    @Override
    public int registerTest(Test test) {
        return testDao.saveTest(test);
    }
}
