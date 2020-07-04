package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Test_Stu_Score implements Serializable {
    private Integer ttss_id;
    private String ttss_title;
    private Integer ttss_base_score;
    private Integer ttss_get_score;
    private Stu_Test ttss_test_id;
}
