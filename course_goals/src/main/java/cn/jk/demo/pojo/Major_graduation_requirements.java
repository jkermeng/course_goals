package cn.jk.demo.pojo;

import cn.jk.demo.pojo.uentity.Major_Graduation_IP_Part;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Major_graduation_requirements implements Serializable {
    private Integer tmgr_id;//毕业要求id
    private String tmgr_tile;//毕业要求标题
    private String tmgr_describe;//毕业要求内容
    private List<Association_Graduation_Requirements> major_index_point;//一个毕业要求下的协会专业指标点
    private List<Major_Graduation_IP_Part> partsList;//毕业要求各部分指标
}
