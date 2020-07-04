package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Test;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TestMapper extends Daos<Test>{
    @Override
    Set<Test> selectsByKey(Test test);

    @Override
    Set<Test> selectAll();

    @Override
    Test selectById(Integer id);

    @Override
    Test selectOneByKey(Test test);

    @Override
    Integer insert(Test test);

    @Override
    Integer update(Test test);

    @Override
    Integer delete(Test test);
}
