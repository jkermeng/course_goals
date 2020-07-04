package cn.jk.demo.server;

import cn.jk.demo.mapper.Association_Graduation_RequirementsMapper;
import cn.jk.demo.pojo.Association_Graduation_Requirements;
import cn.jk.demo.server.imp.Association_Graduation_RequirementsServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class Association_Graduation_RequirementsServImp implements Association_Graduation_RequirementsServI {
    @Autowired
    private Association_Graduation_RequirementsMapper association_graduation_requirementsMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Association_Graduation_Requirements> showAll() {
        List<Association_Graduation_Requirements> list = new ArrayList();
        Set<Association_Graduation_Requirements> association_graduation_requirements = association_graduation_requirementsMapper.selectAll();
        list.addAll(association_graduation_requirements);
        Collections.sort(list, new Comparator<Association_Graduation_Requirements>() {
            @Override
            public int compare(Association_Graduation_Requirements o1, Association_Graduation_Requirements o2) {
                return o1.getTagr_id() - o2.getTagr_id();
            }
        });
        return list;
    }
}
