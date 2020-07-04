package cn.jk.demo.pojo.uentity;

import cn.jk.demo.pojo.Knowledge_Points;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Chapter implements Serializable {
    private String name;
    private Integer num;
    private List<Knowledge_Points> pointsList;
}
