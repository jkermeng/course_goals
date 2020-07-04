package cn.jk.demo.pojo.uentity;

import cn.jk.demo.pojo.Association_Graduation_Requirements;
import cn.jk.demo.pojo.Major_graduation_requirements;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class DefaultKnowledgeMajor implements Serializable {
    private Major_graduation_requirements major_graduation_requirements;//默认的毕业要求
    private List<Association_Graduation_Requirements> Association_Graduation_Requirements;//默认的指标
}
