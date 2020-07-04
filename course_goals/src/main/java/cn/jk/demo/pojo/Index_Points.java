package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Index_Points implements Serializable {
    private Integer tip_id;//id
    private Course table_course_tc_id;//课程
    private Major_graduation_requirements table_major_graduation_requirements_tmgr_id;//专业毕业要求
    private String tip_describe;//描述
    private String tip_weight_level;//权重等级H、M、L
    private Integer tip_weight;//权重分值
    private Integer tip_part;//指标中的第几部分1.1或者2.1

}
