package cn.jk.demo.server;

import cn.jk.demo.mapper.Major_graduation_requirementsMapper;
import cn.jk.demo.pojo.Major_graduation_requirements;
import cn.jk.demo.server.imp.Major_graduation_requirementsServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class Major_graduation_requirementsServImp implements Major_graduation_requirementsServI {
    @Autowired
    private Major_graduation_requirementsMapper major_graduation_requirementsMapper;

    @Override
    public List<Major_graduation_requirements> showAll() {
        List<Major_graduation_requirements> arrayList = new ArrayList();
        arrayList.addAll(major_graduation_requirementsMapper.selectAll());
        Collections.sort(arrayList, new Comparator<Major_graduation_requirements>() {
            @Override
            public int compare(Major_graduation_requirements o1, Major_graduation_requirements o2) {
                return o1.getTmgr_id() - o2.getTmgr_id();
            }
        });
        return arrayList;
    }
}
