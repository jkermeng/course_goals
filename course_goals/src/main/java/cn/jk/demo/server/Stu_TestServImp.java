package cn.jk.demo.server;

import cn.jk.demo.mapper.Stu_TestMapper;
import cn.jk.demo.pojo.Stu_Test;
import cn.jk.demo.pojo.User;
import cn.jk.demo.server.imp.Stu_TestServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class Stu_TestServImp implements Stu_TestServI {
    @Autowired
    private Stu_TestMapper stu_testMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Stu_Test> showStu_TestByStuId(Stu_Test stu_test) {
        List<Stu_Test> objects = new ArrayList<>();
        objects.addAll(stu_testMapper.selectsByKey(stu_test));
        Collections.sort(objects, new Comparator<Stu_Test>() {
            @Override
            public int compare(Stu_Test o1, Stu_Test o2) {
                return o1.getTst_id() - o2.getTst_id();
            }
        });
        return objects;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Stu_Test> showAll() {
        List<Stu_Test> stu_tests = new ArrayList<>();
        stu_tests.addAll(stu_testMapper.selectAll());
        Collections.sort(stu_tests, new Comparator<Stu_Test>() {
            @Override
            public int compare(Stu_Test o1, Stu_Test o2) {
                return o1.getTst_id()-o2.getTst_id();
            }
        });
        return stu_tests;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Stu_Test showOneStu_Test(Stu_Test stu_test) {
        return stu_testMapper.selectOneByKey(stu_test);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addStu_Test(Stu_Test stu_test) {
        return stu_testMapper.insert(stu_test);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateStu_Test(Stu_Test stu_test) {
        return stu_testMapper.update(stu_test);
    }
}
