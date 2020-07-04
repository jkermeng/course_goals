package cn.jk.demo.pojo.uentity;

import cn.jk.demo.pojo.Index_Points;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Major_Graduation_IP_Part implements Serializable {
    private Integer num;
    private String descript;
    private List<Index_Points> pointsList;
}
