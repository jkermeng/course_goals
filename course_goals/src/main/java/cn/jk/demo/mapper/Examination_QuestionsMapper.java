package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Examination_Questions;
import cn.jk.demo.pojo.uentity.Examination_Questions_Know_Point;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Examination_QuestionsMapper extends Daos<Examination_Questions> {
    @Override
    Examination_Questions selectById(Integer id);

    @Override
    Integer insert(Examination_Questions examination_questions);

    Integer insertEQ_KP(Examination_Questions_Know_Point examination_questions_know_point);

    @Override
    Set<Examination_Questions> selectsByKey(Examination_Questions examination_questions);

    Set<Examination_Questions> selectOneByTest_id(Integer id);

    @Override
    Integer delete(Examination_Questions examination_questions);

    @Override
    Examination_Questions selectOneByKey(Examination_Questions examination_questions);

    @Override
    Integer update(Examination_Questions examination_questions);

    Set<Examination_Questions_Know_Point> selectEq_Kp(Examination_Questions_Know_Point examination_questions_know_point);

    Set<Examination_Questions_Know_Point> selectEq_Kp2(Examination_Questions_Know_Point examination_questions_know_point);

    Integer delExamination_Questions_Know_Point(Examination_Questions_Know_Point examination_questions_know_point);
}
