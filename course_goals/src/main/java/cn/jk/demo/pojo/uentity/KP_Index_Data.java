package cn.jk.demo.pojo.uentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonIgnoreProperties(value = {"handler"})
public class KP_Index_Data implements Serializable {
    private Integer table_major_g;
    private Integer table_part;
    private Boolean checkBox;
}
