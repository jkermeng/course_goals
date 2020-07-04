package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Test_KP_IP implements Serializable {
    private Integer ttkp_test_ip;//试卷题目
    private Integer ttkp_kp;//试卷知识点
    private Integer ttkp_ip;//指标
    private Integer ttkp_weight;//权重
}
