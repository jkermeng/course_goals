package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Role implements Serializable {
    private Integer role_Id;//角色id
    private String role_Name;//角色名称
    private List<Funs> RoleFuns;//权限功能
}
