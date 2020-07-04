package cn.jk.demo.server;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.jk.demo.mapper.*;
import cn.jk.demo.pojo.*;
import cn.jk.demo.pojo.uentity.OneStu_Test;
import cn.jk.demo.server.imp.CourseServI;
import cn.jk.demo.server.imp.TestServI;
import cn.jk.demo.server.imp.Test_Stu_ScoreServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TestServImp implements TestServI {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private Knowledge_PointsMapper knowledge_pointsMapper;
    @Autowired
    private CourseServI courseServI;
    @Autowired
    private Stu_TestMapper stu_testMapper;
    @Autowired
    private Test_Stu_ScoreServI test_stu_scoreServImp;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Test> showTestsByKeys(Test test) {
        List<Test> tests = new ArrayList<>();
        Set<Test> tests1 = testMapper.selectsByKey(test);
        tests.addAll(tests1);
        return tests;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addTest(Test test) {
        String format = DateUtil.format(new Date(), "yyyy-MM-dd");
        DateTime parse = DateUtil.parse(format, "yyyy-MM-dd");
        test.setTt_CreateTime(parse);
        test.setTt_UpdateTime(parse);
        test.setTt_status(2);
        System.out.println("添加的考试信息..." + test);
        return testMapper.insert(test);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Test showOneTest(Test test) {
        return testMapper.selectOneByKey(test);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Test> showAll() {
        List list = new ArrayList<Test>();
        Set<Test> tests = testMapper.selectAll();
        list.addAll(tests);
        Collections.sort(list, new Comparator<Test>() {
            @Override
            public int compare(Test o1, Test o2) {
                return o1.getTt_course().getTc_id() - o2.getTt_course().getTc_id();
            }
        });
        return list;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<OneStu_Test> showTestsByStuId(User user) {
        List<OneStu_Test> list = new ArrayList();
        List<Stu_Test> arrayList = new ArrayList();
        Set<Stu_Test> stu_tests = stu_testMapper.selectByStuId(user.getU_id());
        arrayList.addAll(stu_tests);
        Collections.sort(arrayList, new Comparator<Stu_Test>() {
            @Override
            public int compare(Stu_Test o1, Stu_Test o2) {
                return o1.getTst_id() - o2.getTst_id();
            }
        });
        for (Stu_Test st : arrayList
        ) {
            OneStu_Test oneStu_test = new OneStu_Test();
            oneStu_test.setStu_test(st);
            oneStu_test.setReach(st.getTst_test_id().getTt_reach());    //达标分值
            oneStu_test.setTest_st(0);//学生成绩
            Test_Stu_Score test_stu_score = new Test_Stu_Score();
            test_stu_score.setTtss_test_id(st);  //用户对应的试卷id
            List<Test_Stu_Score> test_stu_scores = test_stu_scoreServImp.showStuByIdTSS(test_stu_score);//单个试卷对应的所有选题分数
            Collections.sort(test_stu_scores, new Comparator<Test_Stu_Score>() {
                @Override
                public int compare(Test_Stu_Score o1, Test_Stu_Score o2) {
                    return o1.getTtss_id() - o2.getTtss_id();
                }
            });
            for (Test_Stu_Score tss : test_stu_scores
            ) {
                oneStu_test.setTest_st(oneStu_test.getTest_st() + tss.getTtss_get_score());
            }
            oneStu_test.setTest_stu_scores(test_stu_scores);
            list.add(oneStu_test);
        }
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateTest(Test test) {
        return testMapper.update(test);
    }

    @Autowired
    private Test_Stu_ScoreMapper test_stu_scoreMapper;
    @Autowired
    private Test_KP_IPMapper test_kp_ipMapper;
    @Autowired
    private Examination_QuestionsMapper examination_questionsMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deleteTest(Test t) {
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
        return testMapper.delete(t);
    }
}
