package cn.jk.demo.server.imp;

import cn.jk.demo.pojo.Test_Stu_Score;

import java.util.List;

public interface Test_Stu_ScoreServI {
    List<Test_Stu_Score> showStuByIdTSS(Test_Stu_Score test_stu_score);
    Integer addTest_Stu_Score(Test_Stu_Score test_stu_score);
    Integer updateTest_Stu_Score(Test_Stu_Score test_stu_score);
}
