package cn.jk.demo.pojo.uentity;

import cn.jk.demo.pojo.Examination_Questions;
import cn.jk.demo.pojo.Knowledge_Points;
import cn.jk.demo.pojo.Test;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Examination_Questions_Know_Point implements Serializable {
    private Integer teqkp_id;//自身id
    private Examination_Questions teqkp_eq_id;//试题id
    private Knowledge_Points teqkp_kp_id;//知识点id
}
