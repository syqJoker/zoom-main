package com.mksun.ucenterB.service;

import com.mksun.commons.entity.Test;

import java.util.List;

public interface UCenterBTestService {
    public List<Test> queryTestList();
    public Test queryTestById(String id);

    int registerTest(Test test);
}