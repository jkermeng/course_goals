package cn.jk.demo.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.jk.demo.enums.Enums;
import cn.jk.demo.enums.Result;
import cn.jk.demo.mapper.Examination_QuestionsMapper;
import cn.jk.demo.pojo.*;
import cn.jk.demo.pojo.uentity.*;
import cn.jk.demo.server.imp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private TestServI testServI;
    @Autowired
    private Knowledge_PointsServI knowledge_pointsServI;
    @Autowired
    private Examination_QuestionsMapper examination_questionsMapper;
    @Autowired
    private CourseServI courseServI;

    @PostMapping("model")
    private Result getModel(Test test) {
        Test test1 = testServI.showOneTest(test);
        Course course = courseServI.selecyById(test1.getTt_course().getTc_id());
        Knowledge_Points knowledge_points = new Knowledge_Points();
        knowledge_points.setTable_course_tc_id(course);
        List<Knowledge_Points> knowledge_points1 = knowledge_pointsServI.showKnowledge_PointsByKeys(knowledge_points);
        List<Examination_Questions> examination_questions = new ArrayList<>();
        Examination_Questions examination_questions1 = new Examination_Questions();
        examination_questions1.setKnowledgePointsList(knowledge_points1);
        examination_questions.add(examination_questions1);
        test.setEqList(examination_questions);
        test1.setEqList(examination_questions);
        return new Result(Enums.Success, test1);
    }

    @PostMapping("add")
    private Result addTest(Test test, User user, Course course) {
        if (test != null && user != null && course != null) {
            System.out.println("添加的考试信息..." + test);
            test.setTt_teacher(user);
            test.setTt_course(course);

            Integer integer = testServI.addTest(test);
            if (integer > 0) {
//                //添加考题
//                List<Test> tests = testServI.showTestsByKeys(test);
//                Test test1 = tests.get(0);
//                examination_questions.setTeq_test_id(test1);
//                Integer insert = examination_questionsMapper.insert(examination_questions);
//                if (insert > 0) {
//                    //添加试题
//                    if (cknow_point != null) {
//                        //添加知识点
//                        Examination_Questions eq1 = examination_questionsMapper.selectOneByKey(examination_questions);
//                        Examination_Questions_Know_Point eqkp = new Examination_Questions_Know_Point();
//                        eqkp.setTeqkp_eq_id(eq1);//试题id
//                        String[] split = cknow_point.split(",");
//                        for (String ckp : split
//                        ) {
//                            Integer knowId = Integer.valueOf(ckp);
//                            Knowledge_Points knowledge_points = new Knowledge_Points();
//                            knowledge_points.setTkp_id(knowId);
//                            eqkp.setTeqkp_kp_id(knowledge_points);
//                            System.out.println("添加成功后的试题+知识点:" + eqkp);
//                            //添加知识点。。
//                            Integer integer1 = examination_questionsMapper.insertEQ_KP(eqkp);
//                            System.out.println("添加成功是1，不成功是0 >>>>" + integer1);
//                        }
//                        System.out.println("查看是否添加知识点成功...");
//                        return new Result(Enums.Success, "试卷添加成功！");
//                    } else {
//                        return new Result(Enums.Fail, "添加知识点失败！");
//                    }
//                } else {
//                    return new Result(Enums.Fail, "添加试题失败！");
//                }
                return new Result(Enums.Success, "试卷添加成功！");
            } else {
                return new Result(Enums.Fail, "添加考试信息失败...");
            }
        } else {
            return new Result(Enums.Null, "考试信息或教师或课程是空值Null");
        }
    }

    @Autowired
    private Stu_TestServI stu_testServI;
    @Autowired
    private Test_Stu_ScoreServI test_stu_scoreServI;

    @PostMapping("update")
    private Result updateTest(@RequestBody Test test) {
        if (test != null) {
//            System.out.println("修改的考试信息..." + test + "课程id>" + test.getTt_course().getTc_id() + "教师id>" + test.getTt_teacher().getU_id());
            Integer integer = testServI.updateTest(test);
            List<Examination_Questions> eqList = test.getEqList();
            System.out.println(eqList.size());
            Stu_Test stu_test = new Stu_Test();
            stu_test.setTst_test_id(test);
            List<Stu_Test> stu_tests = stu_testServI.showStu_TestByStuId(stu_test);
            for (Examination_Questions eqs : eqList
            ) {
                System.out.println("修改的试题" + eqs.getTeq_id() + "名称" + eqs.getTeq_name());
                examination_questionsServI.updateExamination_Questions(eqs);
                Examination_Questions examination_questions = examination_questionsServI.selectOneByKey(eqs);
                if (examination_questions == null) {
                    System.out.println("额外的试题");
                    System.out.println(eqs.getTeq_name());
                    eqs.setTeq_test_id(test);
                    examination_questionsServI.insert(eqs);
                }
                Test_Stu_Score test_stu_score = new Test_Stu_Score();
                test_stu_score.setTtss_title(eqs.getTeq_name());
                for (Stu_Test st : stu_tests
                ) {
                    test_stu_score.setTtss_test_id(st);
                    List<Test_Stu_Score> test_stu_scores = test_stu_scoreServI.showStuByIdTSS(test_stu_score);
                    for (Test_Stu_Score tss : test_stu_scores
                    ) {
                        if (tss.getTtss_title().equals(eqs.getTeq_name()) && st.getTst_id() == tss.getTtss_test_id().getTst_id()) {
                            System.out.println("数据库里的分值》" + tss.getTtss_base_score());
                            tss.setTtss_base_score(eqs.getTeq_score());
                            System.out.println("修改了" + tss.getTtss_base_score());
                            //执行修改操作
                            test_stu_scoreServI.updateTest_Stu_Score(tss);
                        }
                    }
                }

                Examination_Questions_Know_Point examination_questions_know_point = new Examination_Questions_Know_Point();
                examination_questions_know_point.setTeqkp_eq_id(eqs);
                for (Knowledge_Points kp : eqs.getKnowledgePointsList()
                ) {
                    System.out.println(kp.getTkp_title());
                    if (null != kp.getCheck() && kp.getCheck() == true) {
                        System.out.println("修改为true的知识点");
                        //添加到eq_kp表中
                        System.out.println(eqs.getTeq_id() + "::::::::" + kp.getTkp_id());

                        examination_questions_know_point.setTeqkp_kp_id(kp);
                        List<Examination_Questions_Know_Point> examination_questions_know_points = examination_questionsServI.selectEQKP2(examination_questions_know_point);
                        if (examination_questions_know_points.size() == 0) {
                            //判断表里是否存在
                            //否则添加入表中
                            Examination_Questions examination_questions1 = examination_questionsServI.selectOneByKey(eqs);
                            examination_questions_know_point.setTeqkp_eq_id(examination_questions1);
                            examination_questionsServI.insertEQ_KP(examination_questions_know_point);
                        }
                    } else {
                        System.out.println("删除不为true的知识点");
                        //从eq_kp表中删除
                        examination_questions_know_point.setTeqkp_kp_id(kp);
                        examination_questionsServI.delExamination_Questions_Know_Point(examination_questions_know_point);
                    }
                }
            }
//            testServI.updateTest(test);
            if (integer > 0) {
                return new Result(Enums.Success, "试卷修改成功！");
            } else {
                return new Result(Enums.Fail, "修改考试信息失败...");
            }
        } else {
            return new Result(Enums.Null, "考试信息或教师或课程是空值Null");
        }
    }

    @GetMapping("showTestById")
    private Result showTestById(User user) {
        if (user != null) {
            System.out.println("学生id+" + user.getU_id());
            List<OneStu_Test> oneStu_tests = testServI.showTestsByStuId(user);
            return new Result(Enums.Success, oneStu_tests);
        } else {
            return new Result(Enums.Null, "用户id是空值");
        }
    }

    @Autowired
    private Examination_QuestionsServI examination_questionsServI;

    @GetMapping("showAll")
    private Result showAll() {
        List<Course> courses = courseServI.selectAll();//所有课程
        List<Test> tests = testServI.showAll();//所有试卷
        List<DefaultTest> defaultTests = new ArrayList<>();

        for (Course c : courses
        ) {
            int num = 0;
            DefaultTest defaultTest = new DefaultTest();
            List<Test> testAdd = new ArrayList<>();
            for (Test tt : tests
            ) {
                if (tt.getTt_course().getTc_id() == c.getTc_id()) {
                    num++;
                    testAdd.add(tt);
                    //查看每一个试卷的考试题目
                    Examination_Questions examination_questions = new Examination_Questions();
                    examination_questions.setTeq_test_id(tt);
                    List<Examination_Questions> examination_questions1 = examination_questionsServI.selectExamination_QuestionsByKeys(examination_questions);
                    //判断试题下的知识点有被选中
                    Knowledge_Points knowledge_points = new Knowledge_Points();
                    knowledge_points.setTable_course_tc_id(tt.getTt_course());

                    for (Examination_Questions eqs : examination_questions1
                    ) {
                        List<Knowledge_Points> knowledge_points1 = knowledge_pointsServI.showKnowledge_PointsByKeys(knowledge_points);
                        Examination_Questions_Know_Point examination_questions_know_point = new Examination_Questions_Know_Point();
                        examination_questions_know_point.setTeqkp_eq_id(eqs);
                        List<Examination_Questions_Know_Point> examination_questions_know_points = examination_questionsServI.selectEQKP(examination_questions_know_point);

                        for (Knowledge_Points kps : knowledge_points1
                        ) {

                            for (Examination_Questions_Know_Point eqkp : examination_questions_know_points
                            ) {
                                if (eqs.getTeq_id() == eqkp.getTeqkp_eq_id().getTeq_id() && eqkp.getTeqkp_kp_id().getTkp_id() == kps.getTkp_id()) {
                                    //试卷试题下的知识点被勾选了
                                    kps.setCheck(true);
                                    break;
                                }

                            }

                        }
                        eqs.setKnowledgePointsList(knowledge_points1);
                    }
                    tt.setEqList(examination_questions1);
                }
            }
            defaultTest.setNumTest(num);
            defaultTest.setTestList(testAdd);
            defaultTest.setCourse(c);
            defaultTests.add(defaultTest);
        }

        return new Result(Enums.Success, defaultTests);
    }

    @GetMapping("showAllTest")
    private Result showAllTest() {
        List<Test> tests = testServI.showAll();

        return new Result(Enums.Success, tests);
    }

    @GetMapping("showControlTest")
    private Result showTest() {
        List<Test> tests = testServI.showAll();
        List<Course> courseList = courseServI.selectAll();//所有课程
        List<Stu_Test> stu_tests = stu_testServI.showAll();
        List<ShowControlTest> showControlTests = new ArrayList<>();
        for (Course course : courseList
        ) {
            List<Stu_Test> stuList = new ArrayList<>();
            ShowControlTest showControlTest = new ShowControlTest();
            showControlTest.setCourse(course);
            for (Stu_Test stu : stu_tests
            ) {
                for (Test t : tests
                ) {
                    if (course.getTc_id() == t.getTt_course().getTc_id() && stu.getTst_test_id().getTt_id() == t.getTt_id()) {
                        stuList.add(stu);
                    }
                }

            }
            showControlTest.setTestNum(stuList.size());
            showControlTest.setStu_testList(stuList);
            showControlTests.add(showControlTest);
        }


        return new Result(Enums.Success, showControlTests);
    }

    @PostMapping("changeReach")
    private Result changeReach(Test test) {
        Integer integer = testServI.updateTest(test);
        if (integer > 0) {
            return new Result(Enums.Success, "修改成功");
        } else {
            return new Result(Enums.Fail, "修改失败！！");
        }
    }

    @PostMapping("del")
    private Result delTest(Test test) {
        System.out.println("将被删除的试卷" + test.getTt_id());
        Integer integer = testServI.deleteTest(test);
        return new Result(Enums.Success, "数据删除成功");
    }
}
