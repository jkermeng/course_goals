package cn.jk.demo.pojo.uentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class UploadStuScore implements Serializable {
    private String testName;
    private String StuNumber;
    private String name;
    private Integer everyScore[];
    private Integer scoreTotal;

}
