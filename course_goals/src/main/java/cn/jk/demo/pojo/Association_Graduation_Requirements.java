package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Association_Graduation_Requirements implements Serializable {
    private Integer tagr_id;//协会毕业id
    private String tagr_title;//协会毕业名称
    private String tagr_describe;//协会毕业名称
    private Boolean checkBox;//是否勾选checBox;
    private List<Index_Points> index_points;
}
