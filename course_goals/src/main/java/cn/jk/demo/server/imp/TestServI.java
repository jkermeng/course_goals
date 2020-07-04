package cn.jk.demo.server.imp;

import cn.jk.demo.pojo.Test;
import cn.jk.demo.pojo.User;
import cn.jk.demo.pojo.uentity.OneStu_Test;

import java.util.List;

public interface TestServI {
    List<Test> showTestsByKeys(Test test);

    Integer addTest(Test test);
    Test showOneTest(Test test);
    List<Test> showAll();

    List<OneStu_Test> showTestsByStuId(User user);

    Integer updateTest(Test test);

    Integer deleteTest(Test test);
}
