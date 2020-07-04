package cn.jk.demo.server;

import cn.jk.demo.mapper.KP_index_PointsMapper;
import cn.jk.demo.pojo.KP_index_Points;
import cn.jk.demo.server.imp.KP_index_PointsServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class KP_index_PointsServImp implements KP_index_PointsServI {
    @Autowired
    private KP_index_PointsMapper kp_index_pointsMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<KP_index_Points> showBytkp_id(KP_index_Points kp_index_points) {
        List list = new ArrayList();
        list.addAll(kp_index_pointsMapper.selectsByKey(kp_index_points));
        Collections.sort(list, new Comparator<KP_index_Points>() {
            @Override
            public int compare(KP_index_Points o1, KP_index_Points o2) {
                return o1.getTip_kp_id() - o2.getTip_kp_id();
            }
        });
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer delKP_index_Points(KP_index_Points kp_index_points) {
        return kp_index_pointsMapper.delete(kp_index_points);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer del2KP_index_Points(KP_index_Points kp_index_points) {
        return kp_index_pointsMapper.delete2(kp_index_points);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addKP_index_Points(KP_index_Points kp_index_points) {
        return kp_index_pointsMapper.insert(kp_index_points);
    }

}
