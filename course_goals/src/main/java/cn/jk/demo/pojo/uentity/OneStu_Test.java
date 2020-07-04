package cn.jk.demo.pojo.uentity;

import cn.jk.demo.pojo.Stu_Test;
import cn.jk.demo.pojo.Test_Stu_Score;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class OneStu_Test implements Serializable {
    private Stu_Test stu_test;//学生的试卷
    private Integer test_st;//学生成绩
    private Integer reach;//达标
    private List<Test_Stu_Score> test_stu_scores;
}
