package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Stu_Test implements Serializable {
    private Integer tst_id;//学生试卷id
    private String tst_test_name;//试卷名称
    private String tst_term;//学期
    private String tst_test_type;//试卷类型
    private String tst_test_second_test;//是否补考
    private User tst_teacher;//老师信息
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date tst_upload;//上传文件时间
    private User tst_stuid;//学生id
    private Test tst_test_id;//试卷id

}
