package cn.jk.demo.server;

import cn.jk.demo.mapper.AdminMapper;
import cn.jk.demo.pojo.Admin;
import cn.jk.demo.server.imp.AdminServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServImp implements AdminServI {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Admin> showAll() {
        return adminMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Admin Login(Admin admin) {
        Admin admin1 = adminMapper.selectOneByKey(admin);
        if (null != admin1 && admin1.getId() != null && admin1.getId() != 0) {
            System.out.println("执行登录操作！！！！！");
            return admin1;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer add(Admin admin) {
        return adminMapper.insert(admin);
    }
}
