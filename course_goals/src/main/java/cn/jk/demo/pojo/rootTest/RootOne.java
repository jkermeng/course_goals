package cn.jk.demo.pojo.rootTest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class RootOne implements Serializable {
    private String name;
    private String url;
    private Address address;
    private List<ArrayBrowser> arrayBrowser;
}
