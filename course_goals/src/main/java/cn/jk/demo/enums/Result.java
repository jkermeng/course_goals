package cn.jk.demo.enums;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object obj;

    public Result() {
    }

    public Result(Enums enums) {
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }

    public Result(Enums enums, Object obj) {
        this.code = enums.getCode();
        this.msg = enums.getMsg();
        this.obj = obj;
    }

}
