package cn.jk.demo.server;

import cn.jk.demo.mapper.Index_PointsMapper;
import cn.jk.demo.pojo.Index_Points;
import cn.jk.demo.server.imp.Index_PointsServI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Index_PointsServImp implements Index_PointsServI {
    @Autowired
    private Index_PointsMapper index_pointsMapper;

    @Override
    public List<Index_Points> showAll() {
        Set<Index_Points> index_points = index_pointsMapper.selectAll();
        List<Index_Points> list = new ArrayList<>();
        list.addAll(index_points);
        Collections.sort(list, new Comparator<Index_Points>() {
            @Override
            public int compare(Index_Points o1, Index_Points o2) {
                return o1.getTip_id()-o2.getTip_id();
            }
        });
        return list;
    }
}
