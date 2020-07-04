package cn.jk.demo.server;

import cn.jk.demo.mapper.*;
import cn.jk.demo.pojo.*;
import cn.jk.demo.server.imp.CourseServI;
import cn.jk.demo.server.imp.Knowledge_PointsServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CourseServImp implements CourseServI {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Course> selectAll() {
        List list = new ArrayList();
        Set<Course> courses = courseMapper.selectAll();
        list.addAll(courses);
        Collections.sort(list, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getTc_id() - o2.getTc_id();
            }
        });
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Course selecyById(Integer id) {
        return courseMapper.selectById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addCourse(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateMsg(Course course) {
        return courseMapper.update(course);
    }

    @Autowired
    private Knowledge_PointsServI knowledge_pointsServI;

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private Stu_TestMapper stu_testMapper;
    @Autowired
    private Test_Stu_ScoreMapper test_stu_scoreMapper;
    @Autowired
    private Test_KP_IPMapper test_kp_ipMapper;
    @Autowired
    private Examination_QuestionsMapper examination_questionsMapper;

    @Autowired
    private Index_PointsMapper index_pointsMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer delCourse(Course course) {
        Knowledge_Points knowledge_points = new Knowledge_Points();
        knowledge_points.setTable_course_tc_id(course);
        List<Knowledge_Points> knowledge_points1 = knowledge_pointsServI.showKnowledge_PointsByKeys(knowledge_points);
        for (Knowledge_Points kps : knowledge_points1
        ) {
            knowledge_pointsServI.delKnowledge_Points(kps);
        }
        Test test = new Test();
        test.setTt_course(course);
        Set<Test> tests = testMapper.selectsByKey(test);
        for (Test t :
                tests) {
            Stu_Test stu_test = new Stu_Test();
            stu_test.setTst_test_id(t);
            Set<Stu_Test> stu_tests = stu_testMapper.selectsByKey(stu_test);
            //获取所有关于该试卷的学生试卷
            for (Stu_Test st : stu_tests
            ) {
                //删除学生考试得分情况
                Test_Stu_Score test_stu_score = new Test_Stu_Score();
                test_stu_score.setTtss_test_id(st);
                test_stu_scoreMapper.delete(test_stu_score);
                st.setTst_id(null);
                stu_testMapper.delete(st);
            }
            Test_KP_IP test_kp_ip = new Test_KP_IP();
            test_kp_ip.setTtkp_test_ip(t.getTt_id());
            test_kp_ipMapper.delete(test_kp_ip);
            Examination_Questions examination_questions = new Examination_Questions();
            examination_questions.setTeq_test_id(t);
            Set<Examination_Questions> examination_questions1 = examination_questionsMapper.selectsByKey(examination_questions);
            for (Examination_Questions eq : examination_questions1
            ) {
                examination_questionsMapper.delete(eq);
            }
            //删除试题
        }
        Index_Points index_points = new Index_Points();
        index_points.setTable_course_tc_id(course);
        index_pointsMapper.delete(index_points);
        return courseMapper.delete(course);
    }
}
