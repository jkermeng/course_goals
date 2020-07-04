package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Examination_Questions implements Serializable {
    private Integer teq_id;//试题id
    private String teq_name;//试题名称
    private Integer teq_score;//试题得分
    private String teq_describe;//描述
    private Test teq_test_id;//考试id

    private List<Knowledge_Points> knowledgePointsList;//试题下的所有知识点
}
