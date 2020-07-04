package cn.jk.demo.pojo.uentity;

import cn.jk.demo.pojo.Course;
import cn.jk.demo.pojo.Test;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class DefaultTest  implements Serializable {
    private Course course;//课程
    private Integer numTest;//试卷个数
    private List<Test> testList;//试卷
}
