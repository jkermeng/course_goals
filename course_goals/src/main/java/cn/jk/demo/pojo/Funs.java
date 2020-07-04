package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Funs implements Serializable {
    private Integer fun_Id;//页面功能id
    private String fun_Name;//页面功能名
    private String fun_Url;//页面地址
    private String fun_Target;//功能目标
    private Funs fun_Pid;//一级页面
    private List<Funs> funsList;//二级页面Funid
}
