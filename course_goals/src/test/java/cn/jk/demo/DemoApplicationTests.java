package cn.jk.demo;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.jk.demo.mapper.*;

import cn.jk.demo.pojo.*;
import cn.jk.demo.pojo.uentity.Chapter;
import cn.jk.demo.pojo.uentity.Examination_Questions_Know_Point;
import cn.jk.demo.server.imp.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Set;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private CourseServI courseServI;
    @Autowired
    private Knowledge_PointsServI knowledge_pointsMapper;
    @Autowired
    private Knowledge_PointsServI knowledge_pointsServI;
    @Autowired
    private KP_index_PointsMapper kp_index_pointsMapper;
    @Autowired
    private Major_graduation_requirementsMapper major_graduation_requirementsMapper;

    @Test
    void contextLoads() {
//        Knowledge_Points knowledge_points = new Knowledge_Points();
//        Course course = new Course();
//        course.setTc_id(1);
//        knowledge_points.setTable_course_tc_id(course);
//        System.out.println(knowledge_pointsMapper.showByCourseId(knowledge_points));
//        System.out.println(courseServI.selectAll());
//        System.out.println(knowledge_pointsMappers.selectById(2));
//        System.out.println(kp_index_points1);
//        KP_index_Points kp_index_points = new KP_index_Points();
//        Knowledge_Points knowledge_points1 = new Knowledge_Points();
//        knowledge_points1.setTkp_id(1);
//        kp_index_points.setTable_knowledge_points_tkp_id(knowledge_points1);
//        Set<KP_index_Points> kp_index_points1 = kp_index_pointsMapper.selectsByKey(kp_index_points);
//        System.out.println(knowledge_points1+"=="+kp_index_points1.size());
//        System.out.println(major_graduation_requirementsMapper.selectById(1));
    }

    @Test
    void CourseAdd() {
        Course course = new Course();
        course.setTc_id(15);
        course.setTc_name("课程测试添加");
        course.setTc_describe("课程测试添加");
        course.setTc_showid("xxxxxx100");
        User user = new User();
        user.setU_id(23);
        course.setTc_teacher(user);
        course.setTc_score(2.00);
        course.setTc_experimental_results(0);
        course.setTc_usual_performance(0);
        course.setTc_interim_results(0);
        course.setTc_final_exam(100);
        course.setTc_report_results(0);
        course.setTc_training_results(0);
        course.setTc_extracurricular_performance(0);
        System.out.println(course);
        Integer integer = courseServI.addCourse(course);
        System.out.println(integer);
    }

    @Autowired
    private UserServI userServI;

    @Test
    void deleteStu() {
        List<User> users = userServI.showAll();
        PageHelper.startPage(1, 10);
        PageInfo pageHelper = new PageInfo(users);
        System.out.println(pageHelper.getList().size());
    }

    @Test
    void CourseTest() {
        Course course = new Course();
        course.setTc_id(25);
        Knowledge_Points knowledge_points = new Knowledge_Points();
        knowledge_points.setTkp_id(41);
        Integer integer = knowledge_pointsServI.delKnowledge_Points(knowledge_points);
//        Integer integer = courseServI.delCourse(course);
        System.out.println(integer);
    }

    @Autowired
    private Examination_QuestionsMapper examination_questionsMapper;

    @Test
    void Test2() {
        Examination_Questions_Know_Point examination_questions_know_point = new Examination_Questions_Know_Point();
        Examination_Questions examination_questions = new Examination_Questions();
        Knowledge_Points knowledge_points = new Knowledge_Points();
        examination_questions.setTeq_id(2);
        knowledge_points.setTkp_id(1);
        examination_questions_know_point.setTeqkp_eq_id(examination_questions);
        examination_questions_know_point.setTeqkp_kp_id(knowledge_points);
        examination_questionsMapper.insertEQ_KP(examination_questions_know_point);
    }

    @Autowired
    private Stu_TestMapper stu_testMapper;
    @Autowired
    private Ass_MajorMapper ass_majorMapper;
    @Autowired
    private Association_Graduation_RequirementsMapper association_graduation_requirementsMapper;
    @Autowired
    private TestServI testServI;
    @Autowired
    private Examination_QuestionsServI examination_questionsServI;

    @Test
    void Test3() {
        //考试
//        cn.jk.demo.pojo.Test test = new cn.jk.demo.pojo.Test();
//        test.setTt_name("测试添加的数据...");
//        String format = DateUtil.format(new Date(), "yyyy-MM-dd");
//        DateTime parse = DateUtil.parse(format, "yyyy-MM-dd");
//        test.setTt_CreateTime(parse);
//        test.setTt_UpdateTime(parse);
//        test.setTt_status(2);
//        User user = new User();
//        user.setU_id(3);
//        test.setTt_teacher(user);
//        Course course = new Course();
//        course.setTc_id(3);
//        test.setTt_course(course);
//        Integer integer = testServI.addTest(test);
//        System.out.println("添加成功" + integer);
//        List<cn.jk.demo.pojo.Test> tests = testServI.showTestsByKeys(test);
//        System.out.println(tests.size());
//        System.out.println(tests.get(0));
//        cn.jk.demo.pojo.Test test1 = tests.get(0);
//        Examination_Questions examination_questions = new Examination_Questions();
//        examination_questions.setTeq_test_id(test1);
//        examination_questions.setTeq_name("第一题测试题目");
//        examination_questions.setTeq_score(20);
//        examination_questions.setTeq_describe("用于系统测试");
//        Integer insert = examination_questionsServI.insert(examination_questions);
//        System.out.println("成功插入测试题目"+insert);
//        Examination_Questions eq1 = examination_questionsServI.selectOneByKey(examination_questions);
//        System.out.println("查询到刚刚插入的测试题目"+eq1.getTeq_id());
//        Examination_Questions_Know_Point examination_questions_know_point = new Examination_Questions_Know_Point();
//        examination_questions_know_point.setTeqkp_eq_id(eq1);
//        Knowledge_Points knowledge_points = new Knowledge_Points();
//        knowledge_points.setTkp_id(2);
//        examination_questions_know_point.setTeqkp_kp_id(knowledge_points);
//        System.out.println("插入数据库中对应的试题下的知识点");
//        Integer integer1 = examination_questionsServI.insertEQ_KP(examination_questions_know_point);
//        System.out.println("试题知识点成功"+integer1);
//        String cknow_point = null;
    }
    @Test
    void test4(){
        String name = "2016春期末考试";

        System.out.println(name.substring(name.length()-5));
    }
}
