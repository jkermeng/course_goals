package cn.jk.demo.mapper;

import cn.jk.demo.pojo.KP_index_Points;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public interface KP_index_PointsMapper extends Daos<KP_index_Points>{
    @Override
    HashSet<KP_index_Points> selectsByKey(KP_index_Points kp_index_points);
    HashSet<KP_index_Points> selectsBytkp_id(Integer id);
    @Override
    Integer delete(KP_index_Points kp_index_points);
    Integer delete2(KP_index_Points kp_index_points);
}
