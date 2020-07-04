package cn.jk.demo.server.imp;

import cn.jk.demo.pojo.Stu_Test;
import cn.jk.demo.pojo.User;

import java.util.List;

public interface Stu_TestServI {
    List<Stu_Test> showStu_TestByStuId(Stu_Test stu_test);

    List<Stu_Test> showAll();

    Stu_Test showOneStu_Test(Stu_Test stu_test);

    Integer addStu_Test(Stu_Test stu_test);

    Integer updateStu_Test(Stu_Test stu_test);
}
