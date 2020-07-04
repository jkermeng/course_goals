package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Test_Stu_Score;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Test_Stu_ScoreMapper extends Daos<Test_Stu_Score>{
    @Override
    Set<Test_Stu_Score> selectsByKey(Test_Stu_Score test_stu_score);

    @Override
    Integer insert(Test_Stu_Score test_stu_score);

    @Override
    Integer update(Test_Stu_Score test_stu_score);

    @Override
    Integer delete(Test_Stu_Score test_stu_score);
}
