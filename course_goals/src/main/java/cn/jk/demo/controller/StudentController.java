package cn.jk.demo.controller;

import cn.jk.demo.enums.Enums;
import cn.jk.demo.enums.Result;
import cn.jk.demo.pojo.Stu_Test;
import cn.jk.demo.pojo.User;
import cn.jk.demo.server.imp.Stu_TestServI;
import cn.jk.demo.server.imp.TestServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private TestServI testServI;
    @Autowired
    private Stu_TestServI stu_testServI;
    @PostMapping("showScore")
    private Result showScore(User user){
        System.out.println("学生id》"+user.getU_id());
        //根据学生的id查询所有试卷
        Stu_Test stu_test = new Stu_Test();
        stu_test.setTst_stuid(user);
        List<Stu_Test> stu_tests = stu_testServI.showStu_TestByStuId(stu_test);
        System.out.println("有关该学生id的所有试卷数量"+stu_tests.size());
        return new Result(Enums.Success,stu_tests);
    }
}
