package cn.jk.demo.pojo;

import cn.jk.demo.pojo.uentity.DefaultKnowledgeMajor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Knowledge_Points implements Serializable {
    //可以修改的，插入
    private Integer tkp_id;//知识点id
    private String tkp_title;//知识点名
    private String tkp_describe;//描述
    private Course table_course_tc_id;//课程
    private Integer tkp_part;//1.1或2.1部分

    //单独属性
//    private Set<KP_index_Points> kp_index_points;//对应指标点
    private List<DefaultKnowledgeMajor> defaultKnowledgeMajors;//对应指标点
    private Boolean check;
}
