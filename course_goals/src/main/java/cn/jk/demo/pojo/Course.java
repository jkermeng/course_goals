package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Course implements Serializable {
    private Integer tc_id;
    private String tc_name;
    private User tc_teacher;
    private String tc_showid;
    private Double tc_score;
    private String tc_describe;
    private Integer tc_usual_performance;
    private Integer tc_experimental_results;
    private Integer tc_interim_results;
    private Integer tc_final_exam;
    private Integer tc_report_results;
    private Integer tc_training_results;
    private Integer tc_extracurricular_performance;
}
