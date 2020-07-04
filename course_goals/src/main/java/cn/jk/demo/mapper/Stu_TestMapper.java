package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Stu_Test;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Stu_TestMapper extends Daos<Stu_Test>{
    @Override
    Set<Stu_Test> selectsByKey(Stu_Test stu_test);

    @Override
    Set<Stu_Test> selectAll();

    @Override
    Integer delete(Stu_Test stu_test);

    @Override
    Stu_Test selectById(Integer id);

    Set<Stu_Test> selectByStuId(Integer id);

    @Override
    Stu_Test selectOneByKey(Stu_Test stu_test);

    @Override
    Integer update(Stu_Test stu_test);

    @Override
    Integer insert(Stu_Test stu_test);
}
