package cn.jk.demo.server;

import cn.jk.demo.mapper.Ass_MajorMapper;
import cn.jk.demo.pojo.Ass_Major;
import cn.jk.demo.server.imp.Ass_MajorServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class Ass_MajorServImp implements Ass_MajorServI {
    @Autowired
    private Ass_MajorMapper ass_majorMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Ass_Major> showAll() {
        List<Ass_Major> ass_majors = new ArrayList<>();
        HashSet<Ass_Major> ass_majors1 = ass_majorMapper.selectAll();
        ass_majors.addAll(ass_majors1);
        Collections.sort(ass_majors, new Comparator<Ass_Major>() {
            @Override
            public int compare(Ass_Major o1, Ass_Major o2) {
                return o1.getTable_major_graduation_requirements_tmgr_id().getTmgr_id() - o2.getTable_major_graduation_requirements_tmgr_id().getTmgr_id();
            }
        });
        return ass_majors;
    }

    @Override
    public List<Ass_Major> showAlls() {
        System.out.println("操作了一次Ass_MajorServImp中的showAll");
        Set<Ass_Major> ass_majors = ass_majorMapper.selectAlls();
        System.out.println(ass_majors.hashCode());
        ArrayList<Ass_Major> ass_majors1 = new ArrayList<>();
        ass_majors1.addAll(ass_majors);
        System.out.println("赋值后的list" + ass_majors1.hashCode());
        return ass_majors1;
    }
}
