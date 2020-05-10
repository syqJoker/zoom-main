package com.mksun.ucenter.dao;

import com.mksun.commons.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestDao {
    List<Test> queryTestList();
    Test queryTestById(String id);

    int saveTest(Test test);
}
