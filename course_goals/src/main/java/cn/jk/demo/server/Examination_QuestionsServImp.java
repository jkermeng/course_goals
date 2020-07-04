package cn.jk.demo.server;

import cn.jk.demo.mapper.Examination_QuestionsMapper;
import cn.jk.demo.pojo.Examination_Questions;
import cn.jk.demo.pojo.uentity.Examination_Questions_Know_Point;
import cn.jk.demo.server.imp.Examination_QuestionsServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class Examination_QuestionsServImp implements Examination_QuestionsServI {
    @Autowired
    private Examination_QuestionsMapper examination_questionsMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Examination_Questions selectById(Integer id) {
        return examination_questionsMapper.selectById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer insert(Examination_Questions examination_questions) {
        return examination_questionsMapper.insert(examination_questions);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer insertEQ_KP(Examination_Questions_Know_Point examination_questions_know_point) {
        return examination_questionsMapper.insertEQ_KP(examination_questions_know_point);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Examination_Questions selectOneByKey(Examination_Questions examination_questions) {
        return examination_questionsMapper.selectOneByKey(examination_questions);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Examination_Questions> sselectByTestId(Integer id) {
        List<Examination_Questions> examination_questions = new ArrayList<>();
        Set<Examination_Questions> examination_questions1 = examination_questionsMapper.selectOneByTest_id(id);
        examination_questions.addAll(examination_questions1);
        Collections.sort(examination_questions, new Comparator<Examination_Questions>() {
            @Override
            public int compare(Examination_Questions o1, Examination_Questions o2) {
                return o1.getTeq_id() - o2.getTeq_id();
            }
        });
        return examination_questions;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Examination_Questions> selectExamination_QuestionsByKeys(Examination_Questions examination_questions) {
        List<Examination_Questions> examination_questions1 = new ArrayList<>();
        examination_questions1.addAll(examination_questionsMapper.selectsByKey(examination_questions));
        Collections.sort(examination_questions1, new Comparator<Examination_Questions>() {
            @Override
            public int compare(Examination_Questions o1, Examination_Questions o2) {
                return o1.getTeq_id() - o2.getTeq_id();
            }
        });
        return examination_questions1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateExamination_Questions(Examination_Questions examination_questions) {
        return examination_questionsMapper.update(examination_questions);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer delExamination_Questions_Know_Point(Examination_Questions_Know_Point examination_questions_know_point) {
        return examination_questionsMapper.delExamination_Questions_Know_Point(examination_questions_know_point);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Examination_Questions_Know_Point> selectEQKP(Examination_Questions_Know_Point examination_questions_know_point) {
        ArrayList<Examination_Questions_Know_Point> examination_questions_know_points = new ArrayList<>();
        examination_questions_know_points.addAll(examination_questionsMapper.selectEq_Kp(examination_questions_know_point));
        Collections.sort(examination_questions_know_points, new Comparator<Examination_Questions_Know_Point>() {
            @Override
            public int compare(Examination_Questions_Know_Point o1, Examination_Questions_Know_Point o2) {
                return o1.getTeqkp_id() - o2.getTeqkp_id();
            }
        });
        return examination_questions_know_points;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Examination_Questions_Know_Point> selectEQKP2(Examination_Questions_Know_Point examination_questions_know_point) {
        List<Examination_Questions_Know_Point> examination_questions_know_points = new ArrayList<>();
        examination_questions_know_points.addAll(examination_questionsMapper.selectEq_Kp2(examination_questions_know_point));
        Collections.sort(examination_questions_know_points, new Comparator<Examination_Questions_Know_Point>() {
            @Override
            public int compare(Examination_Questions_Know_Point o1, Examination_Questions_Know_Point o2) {
                return o1.getTeqkp_id()-o2.getTeqkp_id();
            }
        });
        return examination_questions_know_points;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deleteExamination_Questions(Examination_Questions examination_questions) {
        return examination_questionsMapper.delete(examination_questions);
    }
}
