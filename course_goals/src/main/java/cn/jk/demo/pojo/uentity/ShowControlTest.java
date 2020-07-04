package cn.jk.demo.pojo.uentity;

import cn.jk.demo.pojo.Course;
import cn.jk.demo.pojo.Stu_Test;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class ShowControlTest implements Serializable {
    private Course course;
    private Integer testNum;
    private List<Stu_Test> stu_testList;
}
