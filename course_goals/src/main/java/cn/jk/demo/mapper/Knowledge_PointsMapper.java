package cn.jk.demo.mapper;

import cn.jk.demo.pojo.Knowledge_Points;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;


@Repository
public interface Knowledge_PointsMapper extends Daos<Knowledge_Points>{
    @Override
    HashSet<Knowledge_Points> selectAll();

    @Override
    HashSet<Knowledge_Points> selectsByKey(Knowledge_Points knowledge_points);

    @Override
    Knowledge_Points selectById(Integer id);

    @Override
    Knowledge_Points selectOneByKey(Knowledge_Points knowledge_points);

    @Override
    Integer insert(Knowledge_Points knowledge_points);

    @Override
    Integer delete(Knowledge_Points knowledge_points);

    Integer delKP_IP(Integer id);
}
