package cn.jk.demo.pojo.rootTest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Address implements Serializable {
    private String city;
    private String country;

}
