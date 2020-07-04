package cn.jk.demo.server.imp;

import cn.jk.demo.pojo.Examination_Questions;
import cn.jk.demo.pojo.uentity.Examination_Questions_Know_Point;

import java.util.List;

public interface Examination_QuestionsServI {

    Examination_Questions selectById(Integer id);

    Integer insert(Examination_Questions examination_questions);

    Integer insertEQ_KP(Examination_Questions_Know_Point examination_questions_know_point);

    Integer deleteExamination_Questions(Examination_Questions examination_questions);

    Examination_Questions selectOneByKey(Examination_Questions examination_questions);

    List<Examination_Questions> sselectByTestId(Integer id);

    List<Examination_Questions> selectExamination_QuestionsByKeys(Examination_Questions examination_questions);

    Integer updateExamination_Questions(Examination_Questions examination_questions);

    Integer delExamination_Questions_Know_Point(Examination_Questions_Know_Point examination_questions_know_point);

    List<Examination_Questions_Know_Point> selectEQKP(Examination_Questions_Know_Point examination_questions_know_point);

    List<Examination_Questions_Know_Point> selectEQKP2(Examination_Questions_Know_Point examination_questions_know_point);
}
