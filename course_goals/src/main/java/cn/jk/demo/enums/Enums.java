package cn.jk.demo.enums;


import java.io.Serializable;

public enum Enums implements Serializable {
    Success(200, "成功"), Fail(500, "失败"), Null(404, "空值");
    private Integer code;
    private String msg;

    private Enums() {
    }

    public Integer getCode() {
        return code;
    }

    private Enums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
