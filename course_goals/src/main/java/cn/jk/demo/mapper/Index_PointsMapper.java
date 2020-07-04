package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Index_Points;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Index_PointsMapper extends Daos<Index_Points>{
    @Override
    Integer insert(Index_Points index_points);

    @Override
    Set<Index_Points> selectAll();

    @Override
    Integer delete(Index_Points index_points);

    @Override
    Set<Index_Points> selectsByKey(Index_Points index_points);
}
