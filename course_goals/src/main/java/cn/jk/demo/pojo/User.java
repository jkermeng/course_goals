package cn.jk.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component//注解告诉springboot User类
//@ConfigurationProperties(prefix = "user")//声明user可以在.properties中使用
//@PropertySource({"classpath:userbean.properties"})//使用独立bean.properties时用的注解
//@PropertySource(factory = ymlFactory.class, value = "userbean.yml")//使用自定义的yml文件绑定类
@Data
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable {
    private Integer u_id;//用户id
    private String u_name;//用户登录名称
    private String u_nickname;//用户昵称
    private String u_password;//用户秘密
    private String u_rname;//用户真实姓名
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date u_birthday;//用户出生日期
    private String u_adress;//地址
    private String u_email;//邮箱
    private String u_sex;//性别
    private String u_tel;//电话
    private String u_personID;//身份证
    private String u_picture;//头像
    private Integer u_stuta;//用户可用状态
    private String u_m_tel;//紧急电话
    private String u_fixed_tel;//固定电话
    private String u_nation;//民族
    private String u_poli_outlook;//政治面貌
    private String u_native_place;//籍贯
    private String u_religious_belief;//信仰
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date CreateTime;//账户创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date UpdateTime;//账户更新时间
    private Role role_id;//用户角色权限
}