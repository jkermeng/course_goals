package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class KP_index_Points implements Serializable {
    //需要经常修改的点
    private Integer tip_kp_id;//知识点指标对应id
    private Knowledge_Points table_knowledge_points_tkp_id;//知识点id
    private Major_graduation_requirements table_major_g;//毕业要求id
    private Integer table_part;//1.1或2.1部分

}
