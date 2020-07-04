package cn.jk.demo.server.imp;

import cn.jk.demo.pojo.KP_index_Points;

import java.util.List;
import java.util.Set;

public interface KP_index_PointsServI {
    List<KP_index_Points> showBytkp_id(KP_index_Points kp_index_points);
    Integer delKP_index_Points(KP_index_Points kp_index_points);
    Integer addKP_index_Points(KP_index_Points kp_index_points);
    Integer del2KP_index_Points(KP_index_Points kp_index_points);
}
