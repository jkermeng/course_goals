package cn.jk.demo.server.imp;

import cn.jk.demo.pojo.Ass_Major;
import cn.jk.demo.pojo.Knowledge_Points;
import cn.jk.demo.pojo.uentity.Chapter;

import java.util.List;
import java.util.Map;


public interface Knowledge_PointsServI {
    List<Chapter> showByCourseId(Knowledge_Points knowledge_points);
    List<Knowledge_Points> showKnowledge_PointsByKeys(Knowledge_Points knowledge_points);
    Knowledge_Points showByKnowledge_PointsId(Knowledge_Points knowledge_points);
    Integer addKnowledge_Points(Knowledge_Points knowledge_points);
    Knowledge_Points showOneByKeys(Knowledge_Points knowledge_points);
    Integer updateKnowledge_Points(Knowledge_Points knowledge_points);
    Integer delKnowledge_Points(Knowledge_Points knowledge_points);
}
