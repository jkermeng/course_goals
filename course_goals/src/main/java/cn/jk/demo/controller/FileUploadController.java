package cn.jk.demo.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.jk.demo.enums.Enums;
import cn.jk.demo.enums.Result;
import cn.jk.demo.pojo.*;
import cn.jk.demo.pojo.uentity.UploadStuScore;
import cn.jk.demo.server.imp.*;
import cn.jk.demo.util.UExcel;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class FileUploadController {
    @Autowired
    private UExcel uExcel;
    @Autowired
    private TestServI testServI;
    @Autowired
    private UserServI userServI;
    @Autowired
    private Stu_TestServI stu_testServI;
    @Autowired
    private Examination_QuestionsServI examination_questionsServI;
    @Autowired
    private Test_Stu_ScoreServI test_stu_scoreServI;

    @RequestMapping("uploadTermFile")
    private Result uploadTermExcel(MultipartFile excelTermFile, User teacher) throws IOException, BiffException {
        System.out.println("文件名称>" + excelTermFile.getOriginalFilename());
        System.out.println("文件大小>" + (double) excelTermFile.getSize() / 1024 + "/Kb");
        if (excelTermFile.getSize() > 0) {
            UploadStuScore excel = uExcel.excel(excelTermFile.getInputStream());
            Test test = new Test();
            test.setTt_name(excel.getTestName());
            //通过试卷名称查询试卷
            Test test1 = testServI.showOneTest(test);//关键试卷
            User user = new User();
            user.setU_name(excel.getStuNumber());
            //通过学生学号查学生信息
            User user1 = userServI.showOneUser(user);//关键用户
            if (null != test1 && null != user1 && user1.getU_rname().equals(excel.getName())) {
                //判断查询的学生的学号和姓名是否匹配
                Stu_Test stu_test = new Stu_Test();
                stu_test.setTst_test_id(test1);
                stu_test.setTst_teacher(test1.getTt_teacher());
                stu_test.setTst_stuid(user1);
                stu_test.setTst_test_name(test1.getTt_name().substring(test1.getTt_name().length() - 5) + "-" + user1.getU_rname());
                stu_test.setTst_term(test1.getTt_name().substring(0, 4) + "学年");
                String format = DateUtil.format(new Date(), "yyyy-MM-dd");
                DateTime parse = DateUtil.parse(format, "yyyy-MM-dd");
                stu_test.setTst_upload(parse);
                stu_test.setTst_test_second_test("否");
                stu_test.setTst_teacher(teacher);
                stu_test.setTst_test_type("期末成绩");
                System.out.println(stu_test);
                stu_testServI.addStu_Test(stu_test);
                Stu_Test stu_test1 = stu_testServI.showOneStu_Test(stu_test);
                Test_Stu_Score test_stu_score = new Test_Stu_Score();//学生的分值
                test_stu_score.setTtss_test_id(stu_test1);
                //查询考试题目
                List<Examination_Questions> examination_questions = examination_questionsServI.sselectByTestId(test1.getTt_id());
                for (int i = 0; i < excel.getEveryScore().length; i++) {
                    test_stu_score.setTtss_get_score(excel.getEveryScore()[i]);
                    test_stu_score.setTtss_title(examination_questions.get(i).getTeq_name());
                    test_stu_score.setTtss_base_score(examination_questions.get(i).getTeq_score());
                    test_stu_scoreServI.addTest_Stu_Score(test_stu_score);
                }
                //添加
                return new Result(Enums.Success, "上传成功！");
            } else {
                return new Result(Enums.Null, "Excel表格试卷或学生学号有误！!");
            }

        } else {
            return new Result(Enums.Fail, "没有上传任何数据！");
        }
    }
}
