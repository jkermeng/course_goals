package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Test_KP_IP;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Test_KP_IPMapper extends Daos<Test_KP_IP>{
    @Override
    Test_KP_IP selectById(Integer id);

    @Override
    Test_KP_IP selectOneByKey(Test_KP_IP test_kp_ip);

    @Override
    Integer delete(Test_KP_IP test_kp_ip);

    @Override
    Set<Test_KP_IP> selectsByKey(Test_KP_IP test_kp_ip);
}
